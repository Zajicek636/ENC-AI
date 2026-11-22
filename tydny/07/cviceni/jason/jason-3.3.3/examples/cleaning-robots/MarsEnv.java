import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class MarsEnv extends Environment {
    public static final int GSize = 7;
    public static final int GARB = 16;
    private static final List<String> ROBOTS = List.of("r1", "r2", "r3", "r4", "r5");
    private static final int R2 = ROBOTS.indexOf("r2");
    private static final int R3 = ROBOTS.indexOf("r3");
    private static final Literal NEXT = Literal.parseLiteral("next(slot)");
    private static final Literal PICK = Literal.parseLiteral("pick(garb)");
    private static final Literal DROP = Literal.parseLiteral("drop(garb)");
    private static final Literal BURN = Literal.parseLiteral("burn(garb)");
    private static final Logger logger = Logger.getLogger(MarsEnv.class.getName());

    private MarsModel model;

    private static boolean isBurner(int id) {
        return id == R2 || id == R3;
    }

    @Override
    public void init(String[] args) {
        model = new MarsModel();
        new MarsView(model);
        updatePercepts();
    }

    @Override
    public synchronized Collection<Literal> getPercepts(String ag) {
        return super.getPercepts(ag);
    }

    @Override
    public boolean executeAction(String ag, Structure action) {
        int id = ROBOTS.indexOf(ag);
        if (id < 0) return false;
        synchronized (this) {
            Location here = model.getAgPos(id);
            logger.info(ag + " at " + here + " doing: " + action);
            if (isBurner(id)) {
                if (!BURN.equals(action)) return false;
                model.burnGarb(id);
            } else if (NEXT.equals(action)) {
                int slot = here.y * GSize + here.x + 1;
                if (slot >= GSize * GSize) return false;
                model.setAgPos(id, slot % GSize, slot / GSize);
            } else if (action.getFunctor().equals("move_towards") && action.getArity() == 2
                    && action.getTerm(0) instanceof NumberTerm x && action.getTerm(1) instanceof NumberTerm y) {
                try {
                    double tx = x.solve(), ty = y.solve();
                    if (tx != (int)tx || ty != (int)ty || !model.inGrid((int)tx, (int)ty)) return false;
                    model.setAgPos(id, here.x + Integer.compare((int)tx, here.x),
                                       here.y + Integer.compare((int)ty, here.y));
                } catch (jason.NoValueException e) {
                    return false;
                }
            } else if (PICK.equals(action)) {
                if (!model.pickGarb(id, here)) return false;
            } else if (DROP.equals(action)) {
                if (!model.dropGarb(id, here)) return false;
            } else {
                return false;
            }
            updatePercepts();
        }
        informAgsEnvironmentChanged();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    void updatePercepts() {
        clearPercepts();
        for (int id = 0; id < ROBOTS.size(); id++) {
            String name = ROBOTS.get(id);
            Location here = model.getAgPos(id);
            addPercept(Literal.parseLiteral("pos(" + name + "," + here.x + "," + here.y + ")"));
            if (model.carrying[id]) {
                addPercept(Literal.parseLiteral("carrying(" + name + ")"));
            }
            // A delivery at an incinerator is only available to that incinerator.
            if (isBurner(id) ? model.pending[id] > 0 : model.looseGarbage(here)) {
                addPercept(Literal.parseLiteral("garbage(" + name + ")"));
            }
        }
    }

    static class MarsModel extends GridWorldModel {
        private final int[] missedPicks = new int[ROBOTS.size()];
        private final boolean[] carrying = new boolean[ROBOTS.size()];
        private final int[] pending = new int[ROBOTS.size()];

        MarsModel() {
            super(GSize, GSize, ROBOTS.size());
            setAgPos(0, 0, 0);
            setAgPos(R2, 3, 3);
            setAgPos(R3, 6, 1);
            setAgPos(3, 0, 3);
            setAgPos(4, 0, 5);

            add(GARB, 3, 0);
            add(GARB, 6, 0);
            add(GARB, 1, 2);
            add(GARB, 0, 5);
            add(GARB, 6, 6);
        }

        @Override
        public void setAgPos(int id, Location to) {
            Location from = getAgPos(id);
            super.setAgPos(id, to);
            // GridWorldModel clears the agent bit even if another robot stayed here.
            if (from != null && getAgAtPos(from) >= 0) add(AGENT, from);
        }

        private int burnerAt(Location here) {
            if (here.equals(getAgPos(R2))) return R2;
            if (here.equals(getAgPos(R3))) return R3;
            return -1;
        }

        boolean looseGarbage(Location here) {
            return hasObject(GARB, here) && burnerAt(here) < 0;
        }

        boolean pickGarb(int id, Location here) {
            if (carrying[id] || !looseGarbage(here)) return false;
            if (missedPicks[id] == 2 || random.nextBoolean()) {
                carrying[id] = true;
                missedPicks[id] = 0;
                remove(GARB, here);
            } else {
                missedPicks[id]++;
            }
            return true;
        }

        boolean dropGarb(int id, Location here) {
            int burner = burnerAt(here);
            if (!carrying[id] || burner < 0) return false;
            carrying[id] = false;
            pending[burner]++;
            add(GARB, here);
            return true;
        }

        void burnGarb(int id) {
            if (pending[id] > 0 && --pending[id] == 0) remove(GARB, getAgPos(id));
        }
    }

    static class MarsView extends GridWorldView {
        MarsView(MarsModel model) {
            super(model, "Mars World", 600);
            defaultFont = new Font("Arial", Font.BOLD, 16);
            setVisible(true);
            repaint();
        }

        @Override
        public void draw(Graphics g, int x, int y, int object) {
            if (object == GARB) {
                drawObstacle(g, x, y);
                g.setColor(Color.white);
                drawString(g, x, y, defaultFont, "G");
            }
        }

        @Override
        public void drawAgent(Graphics g, int x, int y, Color c, int id) {
            MarsModel mars = (MarsModel)model;
            Location cell = new Location(x, y);
            // Show every robot at a shared delivery point on its own line.
            int row = 0;
            for (int robot = 0; robot < ROBOTS.size(); robot++) {
                if (!cell.equals(mars.getAgPos(robot))) continue;
                Color color = isBurner(robot) ? Color.blue : Color.yellow;
                if (mars.carrying[robot]) color = Color.orange;
                int top = y * cellSizeH + 2 + row++ * 19;
                g.setColor(color);
                g.fillRect(x * cellSizeW + 2, top, cellSizeW - 4, 18);
                g.setColor(isBurner(robot) ? Color.white : Color.black);
                g.setFont(defaultFont);
                g.drawString(ROBOTS.get(robot) + (mars.carrying[robot] ? " - G" : ""),
                             x * cellSizeW + 5, top + 15);
            }
        }
    }
}
