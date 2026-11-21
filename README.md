# ENC-AI

Pracovní a informační prostor pro předmět ENC-AI: podklady z přednášek, zadání a řešení cvičení a příprava na zkoušku. Každý týden má samostatnou přednášku a cvičení.

## Rozcestník

- [Přehled týdnů](tydny/README.md)
- [Materiály ke zkoušce](zkouska/README.md)
- [Pravidla práce v projektu](AGENTS.md)
- [Skill pro zařazování materiálů](.agents/skills/enc-ai-materialy/SKILL.md)

| Týden | Přednáška — téma a materiály | Cvičení — téma a materiály |
| --- | --- | --- |
| [01](tydny/01/README.md) | [Prompt engineering](tydny/01/prednaska/SHRNUTI.md); PDF, 37 stran | Podklady nedodány |
| [02](tydny/02/README.md) | [Pokročilé techniky, routing a agentní systémy](tydny/02/prednaska/SHRNUTI.md); PDF, 40 stran | Podklady nedodány |
| [03](tydny/03/README.md) | [Agenti, prostředí a formulace problému](tydny/03/prednaska/SHRNUTI.md); PDF, 43 stran | [Konzistence promptů](tydny/03/cviceni/SHRNUTI.md); výsledkový dokument s metodickými omezeními a [výtah teorie](tydny/03/prednaska/TEORIE.md) |
| [04](tydny/04/README.md) | [Rozhodování, plánování a BFS/DFS](tydny/04/prednaska/SHRNUTI.md); PDF, 59 stran | [Multiagentní architektury a ReAct](tydny/04/cviceni/SHRNUTI.md); vyplněný pracovní list, simulovaný log a [výtah teorie](tydny/04/prednaska/TEORIE.md) |
| [05](tydny/05/README.md) | Přednáška nedodána; [teorie odvozená ze cvičení](tydny/05/prednaska/TEORIE.md) | [Vacuum World 3×3](tydny/05/cviceni/SHRNUTI.md); základní úkoly vyplněny, výzva A nevyplněna |
| [06](tydny/06/README.md) | Přednáška nedodána; [teorie odvozená ze cvičení](tydny/06/prednaska/TEORIE.md) | [Iterované vězňovo dilema a evoluční strategie](tydny/06/cviceni/SHRNUTI.md); notebook a Python moduly |

Přednášky a cvičení řadíme podle jejich vlastních čísel; jejich témata se ve stejném řádku nemusí shodovat. Tematicky souvisejí přednáška 01 a cvičení 03, přednáška 02 a cvičení 04, přednáška 03 a cvičení 05. Souhrn přednášek ke zkoušce je dostupný jako [Markdown](<zkouska/Cviko 1.md>) a [PDF](<zkouska/Cviko 1.pdf>).

Další týdny přibudou po dodání materiálů. Přítomnost řešení neznamená potvrzené odevzdání ani ověření správnosti.

## Organizace

```text
tydny/NN/
  AGENTS.md          místní pokyny pro práci s podklady
  README.md          přehled týdne
  prednaska/         přednáška nebo označený výtah teorie ze cvičení
    SHRNUTI.md      přehled obsahu a zdrojů
  cviceni/          zadání, notebooky, kód a další podklady
    SHRNUTI.md      přehled úkolů, souborů a známého stavu
zkouska/            studijní materiály a tematické přehledy
```

Číslo týdne zapisujeme alespoň dvěma číslicemi. Původní názvy dodaných souborů zachováváme; nové organizační složky pojmenováváme bez diakritiky. Kód jednotlivých cvičení může používat vlastní potřebnou strukturu, například `strategies/` u týdne 06.

## Přidání materiálů

Projektový skill je uložený v `.agents/skills/enc-ai-materialy/`. Příklad požadavku:

```text
Použij $enc-ai-materialy na složku C:\složka.
```

Lze zadat i více jednotlivých souborů nebo podklady ke zkoušce. Týden a druh materiálu stačí uvést, pokud nejsou jasné z obsahu. Pokud klient skill nenabízí, lze agenta odkázat přímo na jeho `SKILL.md`.

Skill standardně kopíruje podklady, zachovává originály a doplňuje dokumentaci. Pokud chybí přednáška, může teorii obsaženou ve cvičení shrnout do samostatného souboru bez úprav cvičení.
