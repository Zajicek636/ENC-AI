# Shrnutí — Cvičení 3

[Přehled týdne](../README.md) · [Teorie ke cvičení](../prednaska/TEORIE.md)

## Soubory a původ

| Soubor | Účel |
| --- | --- |
| [res-xdvora20-utf8.md](res-xdvora20-utf8.md) | Čitelná kopie výsledkového dokumentu; UTF-8 a normalizované konce řádků, obsah zachován |
| [res-xdvora20.md](res-xdvora20.md) | Přesná kopie originálu ve Windows-1250 |

Zdroj: [res-xdvora20.md](res-xdvora20.md). Zařazení do cvičení 3 určil uživatel. Některé původní značky jsou nahrazené otazníky; při převodu nebyly rekonstruovány. Tento soubor je nově vytvořené shrnutí, nikoli součást původního řešení.

## Část 1 — Audit formátu po úpravě promptu

Dokument popisuje extrakci technických požadavků, rizik a termínů z návrhu interního informačního systému. Porovnává nestrukturovaný prompt s variantou oddělující instrukce, roli, omezení a požadovaný JSON pomocí XML značek.

| Deklarovaný parametr nebo výsledek | Hodnota |
| --- | --- |
| Opakování | 5 běhů na každý prompt |
| Teplota | 0,2 |
| Model | Podle dokumentu stejný pro oba prompty; konkrétní model neuveden |
| Validní JSON — původní prompt | 3/5, tedy 60 % |
| Validní JSON — upravený prompt | 5/5, tedy 100 % |
| Popsané chyby původního promptu | Komentáře mimo JSON a nekonzistentní struktura termínů |

Jde o převzaté souhrny. Úplné prompty, vstupní projektový dokument ani jednotlivé běhy nebyly dodány. Nelze nezávisle ověřit jejich validitu nebo úspěšnost extrakce. Zdroj uvádí, že vstup neobsahoval kalendářní data ani termíny; tato informace nebyla ověřena vůči původnímu vstupu.

## Část 2 — Porovnání klasifikací A, B1 a B2

Zdroj obsahuje tabulku klasifikací deseti komentářů k programu. Povolené labely pro strukturované varianty jsou `APPROVED`, `CHANGES_REQUESTED` a `COMMENT`.

- Varianta A podle popisu řeší jinou úlohu, používá jinou sadu labelů a uvádí jen skupinové odhady. Není přímo srovnatelná s B1/B2.
- B1 a B2 mají v tabulce po deseti položkách a liší se na pozici 4: `COMMENT` oproti `CHANGES_REQUESTED` u komentáře k povinnému parametru `LoadData`.
- B2 podle souhrnu chybně uvádí v úvodu devět komentářů, ale obsahuje deset bloků. Úplný původní výstup B2 dodán nebyl.
- Podle zdroje pořadí nebylo změněno. Jde o porovnání opakovaných výstupů, nikoli provedený test invariance vůči permutaci pořadí.

| Label | B1 | B2 |
| --- | ---: | ---: |
| COMMENT | 6 (60 %) | 5 (50 %) |
| CHANGES_REQUESTED | 3 (30 %) | 4 (40 %) |
| APPROVED | 1 (10 %) | 1 (10 %) |

## Aritmetická kontrola dodané tabulky

Následující hodnoty byly přepočteny z tabulky B1/B2. Potvrzují výpočet nad uvedenými čísly, nikoli původ experimentálních výsledků.

| Metrika | Kontrolní výpočet | Vztah ke zdroji |
| --- | --- | --- |
| Flip rate | 1/10 = 10 % | Odpovídá uvedené změně na pozici 4 |
| Pozorovaná shoda | 9/10 = 0,90 | Odpovídá tabulce |
| Očekávaná shoda z marginálních četností | 0,6 × 0,5 + 0,3 × 0,4 + 0,1 × 0,1 = 0,43 | Odpovídá zdroji |
| Cohenovo κ | (0,90 − 0,43) / (1 − 0,43) ≈ 0,82456 | Při zaokrouhlení na dvě desetinná místa 0,82; zdroj uvádí také 0,83 |
| Jensenova–Shannonova divergence | ≈ 0,00845 bitu | Neodpovídá zdrojové hodnotě 0,003 bitu |

Pro JS divergenci byly použity distribuce `(0,6; 0,3; 0,1)` a `(0,5; 0,4; 0,1)`, jejich průměr a logaritmus o základu 2. Výpočty vysvětluje [teoretický přehled](../prednaska/TEORIE.md).

## Omezení a upřesnění

Zdroj označuje druhou část jako kompletní a uvádí dostupnost variant A/B1/B2, ale předaný soubor obsahuje jejich analýzu a tabulky, nikoli úplné původní vstupy a výstupy. Bez úplného zadání také nelze potvrdit dokončení všech požadovaných částí cvičení.

Stejné pořadí B1/B2 nedovoluje vyhodnotit vliv permutace. Stabilita prvních či posledních pozic neprokazuje absenci primacy nebo recency bias. Ani jedna změna z deseti sama o sobě nedokazuje statistickou nevýznamnost nebo konkrétní příčinu rozdílu.

Doporučení zdroje k dalším experimentům nejsou provedené testy. Izolovaná klasifikace jedním modelem může být srovnávací varianta, ale není automaticky referenční pravdou. Pravidlo v promptu může chyby omezit; nezaručuje odstranění všech flipů ani chyb počtu položek.

## Stav a odevzdání

Dodaný výsledkový dokument byl převzat, nikoli znovu experimentálně ověřen. Správnost experimentů, úplnost řešení, odevzdání, hodnocení a termín nejsou potvrzeny. Při importu neproběhlo žádné API volání ani nové generování výstupů modelu.
