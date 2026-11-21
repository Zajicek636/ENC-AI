# Inteligentní agenti ve Vacuum World 3×3

**Původ:** studijní výtah z [notebooku cvičení 5](../cviceni/xdvora20_2026_ENC_AI_Seminar_05_Lab_Worksheet_Ex.ipynb), jeho teoretického úvodu, zadání a kódu. Samostatná přednáška nebyla dodána. Doplňující vysvětlení a upřesnění implementace jsou označena; původní notebook zůstává beze změn.

[Shrnutí složky](SHRNUTI.md) · [Přehled týdne](../README.md)

## Agent a rámec PEAS

Agent vnímá prostředí pomocí senzorů a ovlivňuje je pomocí efektorů. PEAS popisuje jeho úlohu prostřednictvím míry výkonu, prostředí, efektorů a senzorů.

| Složka | Vacuum World 3×3 |
| --- | --- |
| P — Performance | +10 bodů za odstranění špíny, −1 za pohyb, −1 za vysávání čistého políčka a dodatečných −5 za náraz do zdi |
| E — Environment | Mřížka 3×3, čistá nebo špinavá políčka, jedna pozice agenta |
| A — Actuators | `UP`, `DOWN`, `LEFT`, `RIGHT`, `CLEAN`, `NOOP` |
| S — Sensors | Aktuální řádek, sloupec a příznak špíny: `(r, c, is_dirty)` |

**Upřesnění podle kódu:** při pokusu o pohyb mimo mřížku se odečte cena pohybu i penalizace, celkem 6 bodů. Akce `NOOP` nemění skóre. Index řádku roste směrem dolů, takže `UP` znamená `r - 1`.

## Vnímání a stavový prostor

Agent zná vlastní pozici a stav aktuálního políčka, nikoli celou mapu špíny. Prostor je tedy z pohledu tohoto senzoru částečně pozorovatelný.

Stav prostředí určuje pozice agenta a konfigurace špíny. V mřížce 3×3 je 9 pozic a každé z 9 políček může být ve dvou stavech:

`9 × 2^9 = 4 608 stavů`.

Pro mřížku 4×4 analogicky vychází:

`16 × 2^16 = 1 048 576 stavů`.

**Doplňující vysvětlení:** tento výpočet nepočítá skóre, čas ani interní paměť agenta jako další složky stavu. Ukazuje kombinace pozice a špíny podle zadání; počet konfigurací roste exponenciálně s počtem políček.

## Tři druhy agentů

| Agent | Rozhodování v notebooku | Důsledek |
| --- | --- | --- |
| RandomAgent | Náhodně volí jeden ze čtyř pohybů nebo `CLEAN`, vjem nevyužívá | Může vysávat čistá políčka, ignorovat špínu i narážet do zdí |
| SimpleReflexAgent | Je-li aktuální políčko špinavé, uklidí; jinak se náhodně pohne | Reaguje na špínu, ale neudržuje historii a může se opakovaně vracet či narážet |
| ModelBasedAgent | Uklízí aktuální špínu, jinak míří k dalšímu bodu pevné hadovité trasy | Vnitřní index zajišťuje systematický průchod místo náhodného pohybu |

Obecně reflexní agent rozhoduje podle aktuálního vjemu, zatímco agent s vnitřním stavem může využít také informaci z předchozího průběhu. To je užitečné, když aktuální vjem sám nepopisuje celé prostředí.

**Upřesnění implementace:** dodaný `ModelBasedAgent` má `memory_grid` a `target_index`. Do mapy zapisuje nulu pro aktuální políčko, a to i před vrácením akce `CLEAN`, ale při výběru další akce mapu nečte. Rozhodování řídí index v pevné trase a aktuální vjem. Nelze proto jeho výkon v tomto kódu vysvětlovat tím, že vybírá neuklizená místa podle mapy. Globální kontrolu čistoty a zastavení běhu provádí simulátor.

## Monte Carlo porovnání

Monte Carlo vyhodnocení opakuje simulaci s náhodnými počátečními podmínkami a sleduje souhrnné výsledky. Notebook pro každého agenta provádí 100 běhů s limitem 50 kroků. Počáteční pravděpodobnost špíny je 0,5 a počáteční pozice je náhodná.

Měří se průměrné skóre a průměrný podíl odstraněné počáteční špíny. Vysoký podíl úklidu nemusí znamenat vysoké skóre: agent může při úklidu spotřebovat mnoho pohybů a penalizací. Uložené hodnoty jsou v [shrnutí cvičení](../cviceni/SHRNUTI.md).

**Doplňující vysvětlení:** benchmark nepoužívá stejné počáteční mapy pro jednotlivé agenty ani pevný seed. Výsledky jsou odhadem pro konkrétní náhodné běhy. Pro přesnější srovnání lze při samostatném experimentu použít společné počáteční podmínky, řídit oba generátory náhody a sledovat také variabilitu výsledků. Uložených 100 % úklidu není univerzální zárukou pro jiná prostředí nebo limity kroků.

## Dynamické prostředí

Základní simulátor novou špínu nevytváří. Výzva A požaduje, aby s pravděpodobností 0,05 za krok vznikla špína na náhodně vybraném čistém políčku. Toto rozšíření v dodaném notebooku není implementováno.

V dynamickém prostředí dřívější informace o čistotě zastarává. Agent potřebuje místa znovu kontrolovat; paměť může například obsahovat dobu poslední návštěvy. Pevná opakovaná trasa také umožňuje opětovné návštěvy, ale nereaguje na rozdílné stáří informací.

**Doplňující vysvětlení:** při návrhu dynamického experimentu je potřeba znovu zvolit způsob ukončení a hodnocení. Okamžitá čistota celé mapy nemusí znamenat konec úlohy, pokud může v dalším kroku vzniknout nová špína. Jde o podnět k výzvě, nikoli o provedenou změnu notebooku.

## Otázky pro zopakování

1. Co popisují jednotlivé části PEAS v úloze vysavače?
2. Proč je prostředí částečně pozorovatelné, i když agent zná svou pozici?
3. Jak odvodíš počet stavů pro mřížku 3×3 a 4×4?
4. Jaký vnitřní stav skutečně ovlivňuje rozhodování dodaného modelového agenta?
5. Proč může agent uklidit většinu špíny a přesto mít záporné skóre?
6. Co měří procento úklidu a jak se liší od procenta úspěšných běhů?
7. Co by se muselo změnit v paměti, hodnocení a ukončování simulace při opakovaném vzniku špíny?

Otázky jsou doplněná studijní pomůcka; nejde o dodané zkouškové otázky.
