# Shrnutí — Přednáška 03 a výtah ze cvičení

[Přehled týdne](../README.md)

Ve složce jsou dva odlišné zdroje: dodaná přednáška o agentech a prostředí a dřívější výtah ze cvičení o konzistenci promptů. Číselná shoda neznamená stejné téma.

| Soubor | Obsah |
| --- | --- |
| [Přednáška 03 — PDF, 43 stran](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf) | „Úvod do AI agentů“; dodaná prezentace |
| [TEORIE.md](TEORIE.md) | Struktura promptu, oddělení instrukcí od dat, validace JSON, shoda klasifikací, flip rate, Cohenovo κ, JS divergence a testování vlivu pořadí |

## Orientace v přednášce

Odkazy používají pořadí stránek PDF od 1. Shrnutí není úplným přepisem slidů.

| Strany | Téma |
| --- | --- |
| [1–3](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=1) | Úvod a semestrální projekt |
| [4–9](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=4) | Aktuality a ukázky AI produktů |
| [10–13](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=10) | Typy agentů, vnímání, akce, cíle, model a rozhodování |
| [14–23](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=14) | Pozorovatelnost, stochastičnost, sledovost, dynamika a granularita prostředí |
| [24–26](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=24) | Zjednodušování úloh na příkladu doručovacího dronu |
| [27–32](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=27) | Simulace prostředí a vyhodnocování výkonu agentů |
| [33–43](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=33) | Formulace problému, typy nejistoty a programová reprezentace |

## Hlavní obsah podle slidů

- Přehled agentů zahrnuje jednoduchého reflexního, modelově založeného reflexního, cílově orientovaného, užitkově orientovaného a učícího se agenta. Rozhodování vychází z vjemů, případného vnitřního stavu a požadavků úlohy.
- Prostředí se klasifikuje podle dostupnosti informací, náhodnosti výsledků akcí, návaznosti rozhodnutí, změn v čase a diskrétní či spojité reprezentace.
- Zjednodušení problému může zahrnovat diskretizaci, změnu granularity, omezení akcí nebo zanedbání vybraných vlivů. Prezentace používá příklad autonomního doručovacího dronu.
- Simulátor opakuje získání vjemu, rozhodnutí agenta a změnu prostředí. Hodnoticí varianta přidává výpočet skóre pomocí výkonové funkce.
- Formulace problému vymezuje počáteční stav, akce, přechodovou funkci, cíl, kritérium úspěchu a omezení. Prezentace rozlišuje dobře a špatně definované úlohy, problémy jednoho či více stavů, stochastické a explorativní problémy.

## Doplňující vysvětlení

Na straně 11 je popis užitkového agenta totožný s popisem cílového. Rozdíl spočívá v tom, že užitkový agent porovnává kvalitu výsledků pomocí užitkové funkce, případně očekávaného užitku, nejen splnění cíle.

Příklad vysavače jako epizodického prostředí na straně 18 závisí na zvoleném modelu. Ve Vacuum World ze cvičení 05 akce mění polohu i špínu a ovlivňují další rozhodnutí, takže jde o sekvenční úlohu.

Ve standardním rozlišení je semi-dynamické prostředí takové, kde se během rozhodování nemění samotný svět, ale s časem se mění hodnocení výkonu, například šachy s hodinami. Popis na straně 20 je v tomto ohledu nepřesný; příklad šachů s hodinami se objevuje v tabulce na straně 22. Původní PDF zůstává zachované.

## Organizační informace ze zdroje

[Strany 2–3](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf#page=2) uvádějí semestrální projekt jako 10 % hodnocení, týmy o 2–4 studentech a milníky: zadání/konzultace do 15.10., proposal do 1.11., průběžné shrnutí do 19.11. a finální zprávu s demem do 13.12. Popisují také obsah příspěvku v MS Teams a návrhu projektu.

Jde o převzaté údaje prezentace, nikoli potvrzení jejich aktuálnosti nebo osobního splnění. Organizační aktualizace obsahuje také [přednáška 04](../../04/prednaska/SHRNUTI.md).

## Souvislosti a původ

Typy agentů a lokální vnímání procvičuje [cvičení 05 — Vacuum World](../../05/cviceni/SHRNUTI.md). Téma zdejšího cvičení 03 naopak souvisí s [přednáškou 01](../../01/prednaska/SHRNUTI.md).

Zdroj: [Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf](Lekce_03-Uvod_do_agentu_a_Vlastnosti_prostredi.pdf). PDF bylo převzato beze změn; titulní slide uvádí „Vytvořeno s AI“.

Zdrojem zachovaného `TEORIE.md` je [čitelná kopie výsledkového dokumentu cvičení](../cviceni/res-xdvora20-utf8.md); [původní soubor](../cviceni/res-xdvora20.md) je zachovaný ve Windows-1250. Kontrolní výpočty a metodická upřesnění v tomto výtahu nejsou součástí dodané přednášky ani důkazem nových modelových experimentů.
