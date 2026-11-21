# Multiagentní architektury a ReAct

**Původ:** studijní výtah z [pracovního listu semináře 4](../cviceni/xdvora20_-_Seminar_4_Multi-Agentni_Architektu.md), jeho zadání, návrhů promptů a analytických odpovědí, vytvořený před dodáním přednášek. Dodaná přednáška 04 probírá plánování a prohledávání; tento text zůstává výtahem z multiagentního cvičení. Doplňující vysvětlení jsou označena; zdrojový dokument zůstává beze změn.

[Shrnutí složky](SHRNUTI.md) · [Přehled týdne](../README.md)

## Agentní smyčka ReAct

ReAct propojuje rozhodování o dalším postupu s akcemi a vyhodnocením jejich výsledků. Pracovní list používá tři oddělené části kroku:

| Část | Význam v návrhu |
| --- | --- |
| `thought` | Stručné zdůvodnění volby následující akce |
| `action` | Jedno volání definovaného pseudonástroje s argumenty |
| `observation` | Výsledek dodaný prostředím po vykonání akce |

Agent z výsledku určí další postup nebo práci ukončí. Ve cvičení jsou dostupné návrhy nástrojů pro vyhledání v logu, restart služby a dokončení práce.

**Doplňující vysvětlení:** stručné zdůvodnění akce je uživatelsky čitelný popis rozhodnutí, nikoli zpřístupnění skrytého interního uvažování modelu. XML značky pouze strukturují text. Samotné uvedení názvu nástroje v promptu nepřipojuje žádnou skutečnou funkci a nevytváří výsledek jejího volání.

## Limit kroků a detekce zacyklení

Zadání omezuje běh na čtyři kroky. Pokud se aktuální pozorování shoduje s bezprostředně předchozím, agent nesmí opakovat právě provedenou akci se stejnými argumenty; má zvolit jinou relevantní akci nebo skončit.

Dodané řešení rozlišuje úspěch, blokování, neodstranitelnou chybu a dosažení limitu. Nástroj `dokoncit` je navržen jako terminální akce. Opakování bez nových informací nemusí posunout diagnostiku a může pouze spotřebovávat čas a prostředky.

**Doplňující vysvětlení:** pravidlo o shodných sousedních pozorováních nepokrývá všechny možné smyčky, například střídání dvou různých výsledků. Celkový limit proto plní jinou funkci než detekce opakování. V reálné implementaci musí limity a ukončení hlídat také řídicí program; jejich zápis do promptu není sám o sobě technickou zárukou.

## Víceagentní role a předání práce

Úkol 1.2 rozděluje práci mezi tři role:

| Role | Odpovědnost | Předávaný výsledek |
| --- | --- | --- |
| Architect | Rozpracování požadavku a definice rozhraní | XML specifikace |
| Developer | Implementace podle specifikace | Kód a podklady pro kontrolu |
| Security Auditor | Posouzení bezpečnostních požadavků a důkazů | `APPROVED` nebo `REJECTED` s nálezy |

Při zamítnutí se práce vrací Developerovi. Strukturované předání (*handoff*) vymezuje, co příjemce dostává a co s tím má provést. Návrh ve cvičení obsahuje identifikátor požadavku, odesílatele, příjemce, verzi implementace, kontrakt, odkaz na kód a výsledky testů. Audit přidává rozhodnutí, závažnost nálezů, důkazy a doporučení.

**Upřesnění zdroje:** návrh v úkolu 1.1 začíná rolí Architect a používá tým s Testerem, přestože zadání této části žádá diagnostického agenta. Úkol 1.2 předepisuje Security Auditora. Jde o nesoulad v dodaném návrhu, nikoli o dvě automaticky zaměnitelné role.

**Doplňující vysvětlení:** jednotná struktura zprávy usnadňuje kontrolu úplnosti, ale XML ani JSON samy nezaručují správnost hodnot. Promptové příklady s atributy typu `required` nejsou automaticky vykonávaným validátorem ani formálním XML schématem. Příjemce musí rozumět významu polí a mít přístup k odkazovaným podkladům.

## Bezpečnostní rozhodnutí a důkazy

Navržený auditor kontroluje soulad se specifikací, autentizaci, autorizaci, vstupy, zpracování chyb a ochranu citlivých informací. `APPROVED` má být podložené dostatečnými důkazy a relevantními úspěšnými testy. `REJECTED` v tomto návrhu zahrnuje jak zjištěné nedostatky, tak chybějící údaje nebo nemožnost dokončit audit.

**Doplňující vysvětlení:** chybějící důkaz bezpečnosti není důkaz konkrétní zranitelnosti. Ve zprávě je proto užitečné rozlišit prokázaný nález od neověřené oblasti. Bezpečnostní rozhodnutí se také liší od technického stavu předání zprávy: připravený výsledek ještě nemusí být doručen.

To ilustruje simulace `AuthService`: `TIMEOUT` popisuje příznak, nikoli potvrzenou příčinu. Záznam nepředstavuje skutečné volání nástrojů; připravená zpráva `BLOCKED` nebyla předána, protože pseudonástroj nebyl připojen. Stav `SUCCESS` u nalezení záznamu neznamená, že služba funguje.

## Kontext mezi agenty

*Context pollution* označuje zanesení kontextu nepotřebnými, zastaralými nebo chybnými informacemi. *Attention dispersion* v pracovním listu popisuje rozptýlení pozornosti mezi mnoha informacemi, které může ztížit zohlednění důležitých požadavků.

Strukturovaný výstup může předat pouze potřebné výsledky, omezení, stav a důkazy. Tím může snížit objem kontextu a riziko přenosu zavádějících informací oproti předání celé historie.

**Doplňující vysvětlení:** rozhodující je výběr obsahu, ne pouze formát JSON/XML. Příliš stručné předání může ztratit důležité podmínky a vést k chybnému pokračování. Zpráva má zachovat také nejistoty a nevyřešené otázky, které ovlivňují další práci.

## Generování tokenů a výpočetní práce

Analytická odpověď vychází z autoregresivního modelu, kde pravděpodobnost dalšího tokenu závisí na předchozích tokenech: `P(w_t | w_<t)`. Další generované tokeny představují další výpočetní kroky a kontext pro následující výstup. Mohou pomoci při řešení, ale zvyšují spotřebu tokenů a dobu zpracování.

**Doplňující vysvětlení:** samotná značka `thought` nezvyšuje počet parametrů ani nemění architekturu modelu a nezaručuje správný výsledek. Z delšího textu také nelze automaticky usuzovat na kvalitnější řešení. Pro potřeby tohoto cvičení lze postup dokumentovat stručným zdůvodněním akce a doloženým výsledkem.

## Rozsah podkladů a otázky pro zopakování

Multi-Model Routing a Agentic RAG jsou uvedené v názvu souboru, ale dodané úkoly je podrobně nerozpracovávají. Tento výtah proto nepředstírá úplné pokrytí těchto témat.

1. Jaký je rozdíl mezi akcí, pozorováním a stručným zdůvodněním akce?
2. Proč nestačí pouze detekce shodného pozorování a k čemu slouží limit kroků?
3. Co musí obsahovat předání práce, aby další agent nemusel domýšlet důležité požadavky?
4. Jak se liší stav `BLOCKED`, auditní rozhodnutí `REJECTED` a chyba doručení zprávy?
5. Kdy může předání zkráceného kontextu pomoci a kdy naopak poškodit navazující práci?
6. Proč ani syntakticky správné XML, ani značka `thought` nejsou zárukou správného výsledku?

Otázky jsou doplněná studijní pomůcka; nejde o dodané zkouškové otázky.
