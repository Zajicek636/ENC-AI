# Pokyny pro týden 03

Platí také [kořenová pravidla](../../AGENTS.md). Orientaci poskytuje [přehled týdne](README.md).

- Číslo cvičení 03 určil uživatel. Dodán byl jeden výsledkový Markdown dokument; úplné zadání, prompty, vstupní dokumenty a jednotlivé experimentální výstupy nejsou samostatně dodány.
- `cviceni/res-xdvora20.md` zachovává původní bajty ve Windows-1250. Nečti jej jako UTF-8 a nepřepisuj jeho kódování ani konce řádků.
- `cviceni/res-xdvora20-utf8.md` je odvozená čitelná kopie. Vzniká striktním dekódováním CP1250, náhradou CRCRLF za LF, poté CRLF za LF a zbývajících CR za LF, a zápisem UTF-8 bez BOM. Nesmí obsahovat další redakční opravy. V dokumentaci odkazuj primárně na tuto kopii a uváděj původ.
- Otazníky a neobvyklé značky přítomné už ve zdroji nejsou chyba nového převodu; nerekonstruuj je odhadem. Novou dokumentaci piš v UTF-8.
- Dvě části dokumentu jsou odlišné experimenty. Teplota 0,2 a pět opakování patří k auditu JSON; nepřenášej je bez dokladu na klasifikační experiment.
- Údaj o validním JSON 3/5 versus 5/5 je deklarovaný souhrn, nikoli nově ověřený běh. Konkrétní model není uveden. Při importu nevolej API ani nespouštěj navrhované experimenty.
- Tabulka B1/B2 dovoluje zkontrolovat jeden flip z deseti, shodu 0,9 a distribuce. Kontrolní výpočet dává Cohenovo κ přibližně 0,82456 a JS divergenci přibližně 0,00845 bitu, nikoli zdrojových 0,003 bitu. Odděluj výpočet z dodané tabulky od ověření původních dat.
- B1 a B2 mají podle zdroje stejné pořadí. Nevyvozuj z jejich porovnání přítomnost ani nepřítomnost pozičního zkreslení nebo statistickou nevýznamnost rozdílu. Varianta A řeší jinou úlohu a používá jinou sadu labelů.
- Samostatné volání modelu není automaticky referenční pravda; shoda klasifikací není jejich správnost. XML značky samy nezaručují ochranu před prompt injection. Tyto hranice vysvětluj mimo zachované kopie zdroje.
- Samostatná přednáška 03 „Úvod do AI agentů“ je dodaná jako PDF o 43 stranách. `prednaska/SHRNUTI.md` rozlišuje její obsah od `prednaska/TEORIE.md`, který zůstává výtahem ze cvičení o promptech. Zdrojové označení „KOMPLETNÍ“ ve cvičení nepotvrzuje úplnost dodávky, správnost ani odevzdání.
- Přednášku zařazuj podle čísla lekce, nepřesouvej ji k tematicky souvisejícímu cvičení 05. PDF zachovej beze změn a používej odkazy na skutečné pořadí jeho stránek.
- Organizační údaje o semestrálním projektu přebírej s odkazem na slide a jako údaje zdroje, nikoli jako potvrzení osobního splnění. Pojmová upřesnění (užitek, epizodičnost a semi-dynamika) odděluj od původního textu.
