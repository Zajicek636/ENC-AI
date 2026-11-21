# Vězňovo dilema a evoluční strategie

**Původ:** studijní výtah z [notebooku cvičení 6](../cviceni/2026_ENC_AI_Seminar_06_Veznovo_Dilema_Lab_Worksheet.ipynb), zejména teoretického úvodu, pravidel hry, popisu strategií a simulace. Samostatná přednáška nebyla dodána. Doplňující vysvětlení jsou označena níže; původní cvičení nebylo upraveno.

[Shrnutí složky](SHRNUTI.md) · [Přehled týdne](../README.md)

## Jednorázové vězňovo dilema

Dva hráči současně volí spolupráci (`C`) nebo zradu (`D`). Odměna jednoho hráče závisí na obou tazích. Ve cvičení platí následující matice; dvojice uvádí body hráče v řádku a hráče ve sloupci:

| Hráč 1 / Hráč 2 | C | D |
| --- | --- | --- |
| C | (3, 3) | (0, 5) |
| D | (5, 0) | (1, 1) |

Pokud druhý hráč spolupracuje, zrada přinese 5 místo 3 bodů. Pokud zradí, zrada přinese 1 místo 0 bodů. Pro hráče maximalizujícího vlastní výnos v jednorázové hře je tedy zrada dominantní strategií. Oboustranná zrada je Nashovou rovnováhou, přestože oboustranná spolupráce přináší oběma více bodů.

Konflikt mezi individuální odměnou a společným výsledkem ilustruje také úvodní příklad autonomních agentů soupeřících o sdílené výpočetní zdroje.

## Opakovaná hra

V iterovaném dilematu hráči opakují zápasy a mohou reagovat na historii. Spolupráce může přinést dlouhodobý výnos, zatímco zrada může vyvolat odvetu.

Při známém konečném počtu kol vede za standardních předpokladů racionality zpětná indukce ke zradě od prvního kola: v posledním kole chybí budoucí trest, stejná úvaha se přenese do předposledního kola a dále zpět.

**Doplňující vysvětlení:** tento závěr předpokládá společnou znalost racionality a maximalizaci součtu vlastních bodů v popsané hře. Neznámý konec hry může spolupráci podpořit, ale sám ji nezaručuje; záleží také na protistrategii a významu budoucích výnosů. Náhodná délka zápasu v kódu není sama o sobě důkazem rovnovážné spolupráce.

## Strategie

| Strategie | Pravidlo |
| --- | --- |
| Always Cooperate | Vždy spolupracuje. |
| Always Defect | Vždy zradí. |
| Random | Náhodně volí spolupráci nebo zradu. |
| Tit-for-Tat | Začne spoluprací, poté opakuje poslední tah soupeře. |
| Tit For Two Tats | Zradí po nejméně dvou bezprostředně po sobě jdoucích zradách soupeře; jinak spolupracuje. |
| Grim Trigger | Spolupracuje do první zrady soupeře, pak trvale zrazuje. |
| Win-Stay, Lose-Shift (Pavlov) | Po dobrém výnosu zopakuje vlastní tah, po špatném jej změní. |

V dodané vlastní strategii WSLS jsou dobré výnosy 3 a 5, špatné 0 a 1. V prvním kole strategie spolupracuje. Pro zdejší matici to znamená opakovat vlastní tah, když soupeř spolupracoval, a změnit ho, když soupeř zradil.

**Pojmové upřesnění:** notebook uvádí „Grim Trigger (Pavlov)“, ale jde o odlišná pravidla. Označení Pavlov se obvykle používá pro Win-Stay, Lose-Shift; trvalá odveta po jedné zradě je Grim Trigger.

## Turnaj a evoluční simulace

Turnaj porovnává strategie v opakovaných zápasech a sčítá jejich body. Důležité je celkové skóre proti celé sadě soupeřů, nikoli pouze počet vyhraných jednotlivých zápasů. Tit-for-Tat může těžit z dlouhodobé spolupráce a současně omezovat ztráty okamžitou odvetou.

Evoluční simulace ve cvičení začíná populací strategií, nechává je hrát a podle získaných bodů náhodně vytváří další generaci. Vyšší relativní skóre znamená vyšší pravděpodobnost zastoupení v další generaci. Always Defect může využívat čistě spolupracující hráče; výsledný vývoj však závisí na celé populaci a náhodných střetech.

**Doplňující vysvětlení:** úspěch v jednom turnaji ani převaha v jedné simulaci neprokazují evoluční stabilitu strategie. Konkrétní graf je výsledkem daného modelu a běhu, nikoli univerzálním pořadím strategií.

## Otázky pro zopakování

1. Proč je v jednorázové hře zrada dominantní strategií, když oboustranná spolupráce dává vyšší společný výnos?
2. Jak funguje zpětná indukce při známém konci hry a jaké má předpoklady?
3. V čem se liší odveta a odpouštění u Tit-for-Tat, Tit For Two Tats a Grim Trigger?
4. Jaký další tah provede WSLS po každé ze čtyř kombinací předchozích tahů?
5. Proč může strategie získat vysoké celkové skóre, i když nepřekonává soupeře v jednotlivých zápasech?
6. Proč nelze z jediného evolučního běhu odvodit obecnou stabilitu strategie?

Otázky jsou doplněná studijní pomůcka; nejde o dodané zkouškové otázky.
