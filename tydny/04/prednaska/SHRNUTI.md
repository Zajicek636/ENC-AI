# Shrnutí — Přednáška 04 a výtah ze cvičení

[Přehled týdne](../README.md)

Ve složce jsou dva odlišné zdroje: dodaná přednáška o rozhodování a plánování a dřívější výtah z multiagentního cvičení.

| Soubor | Obsah |
| --- | --- |
| [Přednáška 04 — PDF, 59 stran](2026_Lekce_04_Agent_Decision_Making.pdf) | „Rozhodování agentů a přechodový systém“; dodaná prezentace |
| [TEORIE.md](TEORIE.md) | ReAct, ukončovací podmínky, role agentů, předávání zpráv, bezpečnostní rozhodnutí a práce s kontextem |

## Orientace v přednášce

Odkazy používají pořadí stránek PDF od 1. Shrnutí nenahrazuje úplný výklad ani grafické průchody stavovým prostorem.

| Strany | Téma |
| --- | --- |
| [1–4](2026_Lekce_04_Agent_Decision_Making.pdf#page=1) | Úvod, projekt, konzultace a podmínky zápočtu |
| [5–11](2026_Lekce_04_Agent_Decision_Making.pdf#page=5) | Opakování agentů, prostředí a volby akcí |
| [12–18](2026_Lekce_04_Agent_Decision_Making.pdf#page=12) | Přechodový systém a stavový prostor posuvného hlavolamu |
| [19–27](2026_Lekce_04_Agent_Decision_Making.pdf#page=19) | Deterministické plánování, stromové a grafové hledání, kritéria hodnocení |
| [28–39](2026_Lekce_04_Agent_Decision_Making.pdf#page=28) | BFS, fronta, grafický průchod, úplnost, optimálnost a náročnost |
| [40–59](2026_Lekce_04_Agent_Decision_Making.pdf#page=40) | DFS, zásobník, backtracking, duplicity, vlastnosti a závěr |

## Hlavní obsah podle slidů

- Přechodový systém `(S, T)` popisuje stavy a relaci přechodů. Pro agenty se přidávají akce `A` a přechodová funkce `γ`; plánovací úloha zahrnuje také počáteční a cílové stavy.
- Plán je posloupnost akcí vedoucí z počátečního stavu do cíle. Při hledání se expandují uzly a vzniká strom prohledávání; tentýž stav se v něm může opakovat.
- Strategie hledání se hodnotí podle úplnosti, optimálnosti, časové a paměťové náročnosti. Kontrola duplicit omezuje opakované prohledávání a cykly.
- BFS vybírá uzly s nejmenší hloubkou a používá frontu FIFO. Prezentace ukazuje grafovou verzi a zdůrazňuje její vysoké paměťové nároky.
- DFS upřednostňuje nejhlubší uzly, používá zásobník LIFO a vrací se zpět při vyčerpání větve. Prezentace porovnává varianty s kontrolou duplicit a bez ní; nejkratší řešení obecně nezaručuje.

## Doplňující vysvětlení k předpokladům

BFS nachází řešení s nejmenším počtem hran; to odpovídá nejlevnějšímu řešení při stejné ceně všech kroků. Úplnost při konečné hloubce řešení předpokládá konečné větvení. Pro různé ceny hran se nejmenší počet kroků a nejmenší cena mohou lišit.

Tvrzení na straně 56 o úplnosti grafového DFS „pro každý problém“ je příliš obecné. Kontrola navštívených stavů zajišťuje úplnost na konečném dosažitelném grafu; nekonečná větev v nekonečném prostoru může DFS zabránit v nalezení jiného cíle. Uváděná nízká paměť `O(bm)` se týká příslušné stromové varianty, kde `b` je větvení a `m` maximální hloubka. Uchovávání globální množiny navštívených stavů má další paměťové náklady.

Jde o označená upřesnění, nikoli změny původních slidů. Vzorce a průběhy v diagramových částech je vhodné studovat přímo v PDF.

## Organizační informace ze zdroje

- [Strana 2](2026_Lekce_04_Agent_Decision_Making.pdf#page=2): projekt tvoří 10 % hodnocení, týmy mají 2–4 členy; uvedené milníky jsou 15.10., 1.11., 19.11. a 13.12.
- [Strana 3](2026_Lekce_04_Agent_Decision_Making.pdf#page=3): konzultační okna v týdnu od 26.10., nahrazení přednášky konzultacemi a odevzdání proposal do UIS jedním členem týmu.
- [Strana 4](2026_Lekce_04_Agent_Decision_Making.pdf#page=4): zápočet je uveden jako 40 % hodnocení; osm týdenních úkolů, vstupní kvíz a „Senzorický test“ za 10 bodů s minimem 60 %. Test má pokrývat látku do 6.11., proběhnout na cvičeních od 9.11. a mít možnost opravy.

Tyto informace zachycují obsah prezentace; nejsou potvrzením aktuálních podmínek ani uživatelova splnění. Označení „done“ u vstupního kvízu na slidu není osobní evidence. Zápočtový test není v tomto přehledu zaměňován za termín závěrečné zkoušky.

## Souvislosti a původ

Přednáška navazuje na [agenty a formulaci problému v lekci 03](../../03/prednaska/SHRNUTI.md). Zdejší multiagentní cvičení tematicky souvisí s [přednáškou 02](../../02/prednaska/SHRNUTI.md); jeho řešení ani číselné zařazení se nemění.

Zdroj: [2026_Lekce_04_Agent_Decision_Making.pdf](2026_Lekce_04_Agent_Decision_Making.pdf). PDF je převzaté beze změn a uvádí „Vytvořeno s AI“.

Zdrojem zachovaného `TEORIE.md` je [pracovní list cvičení 4](../cviceni/xdvora20_-_Seminar_4_Multi-Agentni_Architektu.md). Jeho výklad ReAct a multiagentních rolí není odvozený z nově dodané přednášky. Název pracovního listu zmiňuje Multi-Model Routing a Agentic RAG, které samotné úkoly podrobně nerozpracovávají.
