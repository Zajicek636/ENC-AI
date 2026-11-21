# Pokyny pro týden 06

Platí také [kořenová pravidla](../../AGENTS.md). Orientaci poskytuje [přehled týdne](README.md).

- Dodaným zdrojem je vyplněný notebook cvičení a sedm Python souborů. Samostatná přednáška dodána nebyla; `prednaska/TEORIE.md` je odvozený studijní text.
- Notebook zachovej při importu beze změn včetně buněk, metadat a uložených výstupů. Změny řešení prováděj až na základě samostatného požadavku.
- Notebook pracuje s relativní složkou `strategies/`; při případném pozdějším spuštění má být pracovním adresářem `cviceni/`. Moduly zůstávají uvnitř tohoto balíčku, včetně `__init__.py`; `random.py` nepřesouvej vedle notebooku, kde by mohl zastínit standardní modul.
- Buňky 1, 2, 4 a 5 vytvářejí nebo přepisují soubory v `strategies/`. Notebook nespouštěj pouze pro ověření importu. Před pozdějším spuštěním zohledni případné změny samostatných strategií, které by buňky přepsaly.
- Kód notebooku používá NumPy a Matplotlib. Import materiálů neinstaluje prostředí a nepotvrzuje spustitelnost notebooku.
- Rozhraní strategií tvoří `create_strategy()`, `author_name()`, `strategy_name()`, `reset()`, `last_move(my_move, other_move)` a `play()` vracející `C` nebo `D`.
- Zadání používá označení „Grim Trigger (Pavlov)“. Ve studijním textu rozlišuj Grim Trigger od Pavlov/Win-Stay, Lose-Shift; originál kvůli tomuto upřesnění neupravuj.
- Výsledky turnaje a simulace jsou náhodné. Čísla ve studentských odpovědích označ jako převzatá, ne jako nově naměřená či nezávisle ověřená.
