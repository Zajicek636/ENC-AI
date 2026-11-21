# Shrnutí — Přednáška 01: Prompt engineering

[Přehled týdne](../README.md) · [Původní PDF](Lekce_01_-_Prompt_Engineering.pdf)

Dodaná prezentace má **37 stran**. Titulní slide zní „Úvod do světa moderní AI“; označení lekce 01 vychází z názvu souboru. Podle uživatele jde o přednáškový materiál. Následující text shrnuje obsah, nepřepisuje všechny slidy.

## Orientace v přednášce

Odkazy používají pořadí stránek PDF od 1, nikoli případná čísla vytištěná na slidech.

| Strany | Téma |
| --- | --- |
| [1–9](Lekce_01_-_Prompt_Engineering.pdf#page=1) | Představení kurzu a vyučujícího, kontext využití AI a profesní trendy |
| [10–19](Lekce_01_-_Prompt_Engineering.pdf#page=10) | Cíle prompt engineeringu, šest částí promptu, příklady a aktivita s tréninkovým plánem |
| [20–22](Lekce_01_-_Prompt_Engineering.pdf#page=20) | Tokenizace, attention a role zpráv |
| [23–29](Lekce_01_-_Prompt_Engineering.pdf#page=23) | Zero-shot, few-shot, CoT, ToT, rozklad úlohy a ukázky systémového promptu |
| [30–31](Lekce_01_-_Prompt_Engineering.pdf#page=30) | Halucinace, grounding, kontrola výsledků a citace |
| [32–34](Lekce_01_-_Prompt_Engineering.pdf#page=32) | Náklady tokenů, kontextové okno, latence a caching |
| [35–37](Lekce_01_-_Prompt_Engineering.pdf#page=35) | Aktivita „The Clinic“, souhrn a závěr |

## Hlavní obsah podle slidů

- Prompt je popsaný jako návrh vstupu s jasným účelem. Jeho šest složek tvoří persona, kontext, instrukce, omezení, příklady a formát výstupu.
- Tokenizace převádí text na tokeny; token není totéž co slovo. Pozornost propojuje informace v kontextu a velikost kontextu ovlivňuje práci s dlouhými vstupy.
- Few-shot přidává příklady požadovaného chování. Prezentace dále představuje postupné řešení, větvení kandidátních řešení a rozklad složité úlohy do navazujících kroků.
- Pro omezení nepodložených odpovědí doporučuje práci s dodaným zdrojem, nižší teplotu, další kontrolu výstupu a citace.
- Optimalizace rozlišuje vstupní a výstupní tokeny, dobu do prvního tokenu a celkovou dobu generování. Caching je představen jako možnost opakovaného využití stejného vstupu.

## Doplňující vysvětlení

Zero-shot znamená zadání bez demonstračních příkladů dané úlohy; prompt může stále obsahovat instrukce i zdrojový kontext. Nižší teplota může omezit variabilitu, ale nezaručuje pravdivost. Citace a následná kontrola modelu také potřebují ověření proti skutečnému zdroji.

Zjednodušený popis attention ve slidech nerozlišuje všechny architektury; u kauzálního autoregresivního modelu token nemá přístup k budoucím tokenům. Číselné příklady kapacit, cen a úspor jsou údaje prezentace, nikoli aktuálně ověřený přehled služeb. Pojmy CoT a ToT zde popisují výukové strategie; pro zaznamenání postupu lze používat stručná zdůvodnění a ověřitelné výsledky bez požadavku na skryté interní uvažování modelu.

## Souvislosti a původ

K procvičení struktury a konzistence promptů slouží [cvičení 03](../../03/cviceni/SHRNUTI.md). Přednáška není podkladem pro potvrzení jeho správnosti nebo odevzdání.

Zdroj: [Lekce_01_-_Prompt_Engineering.pdf](Lekce_01_-_Prompt_Engineering.pdf). PDF je převzaté beze změn a na titulní straně uvádí „Vytvořeno s AI“. Toto shrnutí je nová dokumentace.
