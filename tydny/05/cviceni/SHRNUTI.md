# Shrnutí — Cvičení 5

[Přehled týdne](../README.md) · [Teorie ke cvičení](../prednaska/TEORIE.md)

## Zadání a soubor

Veškeré dodané podklady obsahuje [notebook cvičení 5](xdvora20_2026_ENC_AI_Seminar_05_Lab_Worksheet_Ex.ipynb). Cílem je porovnat jednoduchého reflexního agenta s agentem s vnitřním stavem v prostředí Vacuum World 3×3.

1. Implementovat `SimpleReflexAgent`: špinavé políčko vysát, jinak zvolit náhodný pohyb.
2. Implementovat `ModelBasedAgent`: přednostně uklízet a jinak postupovat podle hadovité patrolovací trasy.
3. Porovnat oba agenty s `RandomAgent` pomocí Monte Carlo simulace: 100 běhů, nejvýše 50 kroků na běh, počáteční pravděpodobnost špíny 0,5.
4. Vyplnit výsledky, analytické otázky a reflexi v závěrečném protokolu.
5. Výzva A: doplnit vznik nové špíny s pravděpodobností 0,05 za krok a vyhodnotit chování agentů v dynamickém prostředí.

Notebook zahrnuje také simulátor, jednoduché kontroly rozhraní a grafickou ukázku úklidu.

## Stav podkladů

Základní implementace obou agentů jsou doplněné, benchmark má uložený výstup a závěrečný protokol je vyplněný. U kontrol úkolů 1 a 2 jsou uložené zprávy o úspěšném průchodu. Při importu se žádný test ani simulace znovu nespouštěly.

Výzva A zůstává nevyplněná: připravená buňka obsahuje pouze komentář, základní simulátor novou špínu nevytváří. Správnost řešení, odevzdání a hodnocení nejsou potvrzeny.

## Převzaté výsledky

Následující hodnoty jsou uložené v benchmarku i závěrečném protokolu; nejde o nově naměřené nebo nezávisle ověřené výsledky.

| Agent | Průměrné skóre | Průměrný podíl uklizené špíny |
| --- | ---: | ---: |
| RandomAgent | −81,38 | 55,6 % |
| SimpleReflexAgent | −38,68 | 94,3 % |
| ModelBasedAgent | 35,41 | 100,0 % |

Podíl úklidu se počítá vůči počátečnímu množství špíny, nikoli jako podíl úspěšně dokončených běhů. Pro původně čistou mřížku je definován jako 100 %. Prostředí i rozhodování některých agentů jsou náhodné.

## Práce s notebookem a upřesnění

Notebook používá Python 3, NumPy, Matplotlib a IPython a je připravený pro Google Colab. Definice prostředí a agentů musí předcházet vizualizaci a benchmarku. Nevyžaduje další dodané soubory; při importu se nevytvářelo ani netestovalo prostředí.

**Upřesnění podle implementace:** náraz do zdi stojí celkem 6 bodů včetně ceny pohybu. Modelový agent zapisuje navštívená políčka do mapy, ale při rozhodování ji nečte; používá pevnou trasu a její index. Tvrzení ve studentské odpovědi o využití mapy a zaručeném úklidu je proto potřeba odlišit od skutečné implementace a konkrétního uloženého výsledku. Podrobnosti rozvádí [teoretický přehled](../prednaska/TEORIE.md).

## Odevzdání a původ

Zadání žádá vyplnění a odevzdání závěrečného protokolu. Konkrétní termín, způsob odevzdání a potvrzení odevzdání nejsou doloženy.

Zdroj: [xdvora20_2026_ENC_AI_Seminar_05_Lab_Worksheet_Ex.ipynb](xdvora20_2026_ENC_AI_Seminar_05_Lab_Worksheet_Ex.ipynb). Notebook byl zkopírován beze změn včetně uložených výstupů. Toto shrnutí je nová dokumentace.
