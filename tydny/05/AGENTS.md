# Pokyny pro týden 05

Platí také [kořenová pravidla](../../AGENTS.md). Orientaci poskytuje [přehled týdne](README.md).

- Dodaným zdrojem je jeden notebook cvičení. Samostatná přednáška dodána nebyla; `prednaska/TEORIE.md` je odvozený studijní text.
- Při importu zachovej všechny buňky, metadata a uložené výstupy notebooku. Neopravuj řešení ani nedoplňuj výzvu A bez samostatného požadavku.
- Notebook je určen pro Python 3 v Google Colab a používá NumPy, Matplotlib a IPython. Buňky navazují na definice prostředí a agentů v předchozích buňkách. Nejsou potřeba lokální datové soubory ani samostatné moduly; import neinstaluje prostředí a nespouští kód.
- Benchmark používá generátory `random` i `numpy.random` bez nastavení seedu. Pro každého agenta vytváří nová náhodná prostředí; uložené výsledky nejsou reprodukovaným měřením.
- Pohyb stojí 1 bod; při nárazu se odečte dalších 5 bodů, tedy celkem 6. Vysvětlení musí odpovídat skutečnému kódu.
- `ModelBasedAgent` zapisuje do `memory_grid`, ale tuto mapu při volbě akcí nečte. Rozhoduje podle aktuálního vjemu, pevné hadovité trasy a `target_index`. Ukončení po vyčištění celé mřížky zajišťuje simulátor, nikoli úsudek agenta z mapy.
- Výzva A s pravděpodobností vzniku špíny 0,05 není implementovaná. Základní prostředí nové nečistoty nevytváří.
- V přehledech odlišuj vyplněné řešení a uložené úspěšné výstupy testů od nezávislého ověření, odevzdání a hodnocení.
