// move_towards can change both coordinates in one step.
distance_to(R,X,Y,D) :- pos(R,RX,RY) & D = math.max(math.abs(RX-X),math.abs(RY-Y)).

!start.

+!start <- .my_name(Me); !check(Me).

+!check(Me) : pos(Me,X,Y)
    <- if (garbage(Me)) {
           !pick_up(Me);
           ?distance_to(r2,X,Y,D2);
           ?distance_to(r3,X,Y,D3);
           if (D2 <= D3) { !deliver(Me,r2) } else { !deliver(Me,r3) };
           !go_to(Me,X,Y)
       };
       if (X == 6 & last_row(Y)) {
           .print("Patrol complete")
       } else {
           next(slot);
           !check(Me)
       }.

+!pick_up(Me) : carrying(Me).
+!pick_up(Me) : garbage(Me) <- pick(garb); !pick_up(Me).

+!deliver(Me,R) : pos(R,X,Y) <- !go_to(Me,X,Y); drop(garb).

+!go_to(Me,X,Y) : pos(Me,X,Y).
+!go_to(Me,X,Y) <- move_towards(X,Y); !go_to(Me,X,Y).
