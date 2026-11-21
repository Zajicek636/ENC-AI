# Shrnutí — Přednáška 02: Pokročilé techniky

[Přehled týdne](../README.md) · [Původní PDF](2026_Lekce_02_Pokrocile_techniky.pdf)

Dodaná prezentace „Pokročilé techniky v prompt engineeringu“ má **40 stran**. Shrnutí zachycuje témata a orientaci v dokumentu; nejde o úplný přepis.

## Orientace v přednášce

Odkazy používají pořadí stránek PDF od 1.

| Strany | Téma |
| --- | --- |
| [1–9](2026_Lekce_02_Pokrocile_techniky.pdf#page=1) | Profesní a tržní kontext AI, opakování prompt engineeringu |
| [10–11](2026_Lekce_02_Pokrocile_techniky.pdf#page=10) | RLHF, diverzita odpovědí a verbalized sampling |
| [12–16](2026_Lekce_02_Pokrocile_techniky.pdf#page=12) | Pět strategií: vrstvení omezení, obrácený postup, střídání perspektiv, rozklad a zpřesnění cíle |
| [17–25](2026_Lekce_02_Pokrocile_techniky.pdf#page=17) | Role a vzory pro vývojáře, QA a výzkumníky |
| [26–29](2026_Lekce_02_Pokrocile_techniky.pdf#page=26) | Více modelů, routing a výzkumná pipeline |
| [30–35](2026_Lekce_02_Pokrocile_techniky.pdf#page=30) | Chain versus agent, ReAct, paměť a nástroje, multiagentní týmy a rizika |
| [36–40](2026_Lekce_02_Pokrocile_techniky.pdf#page=36) | Verzování promptů, strukturované výstupy, evaluace, knihovna promptů a zdroje |

## Hlavní obsah podle slidů

- Verbalized sampling žádá více kandidátních odpovědí s pravděpodobnostmi. Prezentace jej spojuje s obnovením diverzity odpovědí po zarovnání modelu.
- Pokročilé strategie zpřesňují cíl, vrství podmínky, střídají odborné perspektivy nebo rozdělují problém na dílčí kroky. Role-based vzory zahrnují refaktoring, tvorbu testů, fuzzing a kritickou recenzi.
- Router volí další model nebo postup podle typu a náročnosti úlohy. Výzkumná pipeline rozděluje získávání zdrojů, analýzu a další zpracování mezi různé nástroje či modely.
- Lineární řetězec má předem danou posloupnost kroků; agentní smyčka vybírá další akci podle cíle a získaných pozorování. Komponenty agenta zahrnují personu, paměť, nástroje a plánování.
- Multiagentní architektura rozděluje odpovědnosti mezi role. Přednáška uvádí CrewAI a LangGraph a příklad vývojového týmu.
- Mezi rizika řadí zacyklení, neplatná volání nástrojů a destruktivní akce. Doporučuje lidskou kontrolu, verzování promptů, oddělení dat, formát JSON/XML a evaluaci na referenční sadě.

## Doplňující vysvětlení

Teplota 0 sama není obecnou zárukou opakovatelnosti celého systému. Struktura JSON/XML usnadňuje zpracování a validaci, ale samotné značky nezajišťují ochranu před prompt injection. Deklarované nástroje musí mít skutečnou implementaci a kontrolu vstupů; jejich uvedení v promptu je nespouští.

Výsledky verbalized sampling a srovnání modelů jsou převzatá tvrzení slidů. Nebyly zde reprodukovány a nelze je zobecnit jako zaručený přínos pro každý model nebo úlohu. Pravidla pro lidskou kontrolu jsou obsahem přednášky, nikoli automatickou změnou pracovních pravidel tohoto projektu.

## Souvislosti a původ

Témata ReAct a předávání práce procvičuje [cvičení 04](../../04/cviceni/SHRNUTI.md), jehož log je simulovaný. Přednáška 02 a cvičení 04 zůstávají zařazené podle vlastních čísel.

Zdroj: [2026_Lekce_02_Pokrocile_techniky.pdf](2026_Lekce_02_Pokrocile_techniky.pdf). PDF je převzaté beze změn a uvádí „Vytvořeno s AI“. Toto shrnutí je nová dokumentace.
