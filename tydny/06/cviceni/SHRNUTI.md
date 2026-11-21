# Shrnutí — Cvičení 6

[Přehled týdne](../README.md) · [Teorie ke cvičení](../prednaska/TEORIE.md)

## Téma a zadání

Iterované vězňovo dilema propojuje teorii her, chování autonomních agentů a evoluční výběr strategií. Zadání v [notebooku](2026_ENC_AI_Seminar_06_Veznovo_Dilema_Lab_Worksheet.ipynb) zahrnuje:

1. Seznámení s maticí odměn a základními strategiemi.
2. Implementaci Tit For Two Tats, která zradí po dvou po sobě jdoucích zradách soupeře.
3. Implementaci vlastní pokročilé strategie se zadaným rozhraním.
4. Ověření rozhraní, turnaj každý s každým a evoluční simulaci populace.
5. Analytické odpovědi a sebereflexi výsledků.

## Soubory

| Soubor | Účel |
| --- | --- |
| [Notebook cvičení](2026_ENC_AI_Seminar_06_Veznovo_Dilema_Lab_Worksheet.ipynb) | Teorie, zadání, generování strategií, herní motor, test rozhraní, turnaj, simulace a vyplněné odpovědi |
| [always_cooperate.py](strategies/always_cooperate.py) | Strategie vždy spolupracující |
| [always_defect.py](strategies/always_defect.py) | Strategie vždy zrazující |
| [random.py](strategies/random.py) | Náhodná strategie |
| [titfortat.py](strategies/titfortat.py) | Kopírování posledního tahu soupeře |
| [titfortwotats.py](strategies/titfortwotats.py) | Řešení prvního úkolu: odveta po dvou po sobě jdoucích zradách |
| [moje_strategie.py](strategies/moje_strategie.py) | Vlastní strategie Radek Strategy WSLS |
| [__init__.py](strategies/__init__.py) | Součást Python balíčku strategií |

## Stav dodaných podkladů

Řešení obou implementačních úkolů je přítomno, notebook obsahuje také vyplněné odpovědi a uložené výstupy. Ve studentské sebereflexi je uvedeno páté místo ze šesti s 47 265 body a pokles vlastní strategie z 10 jedinců na jednoho v poslední zobrazené generaci. Tato čísla jsou převzatá ze sebereflexe, při importu nebyla nově měřena ani nezávisle ověřena.

Správnost řešení, odevzdání a hodnocení nejsou potvrzeny. Import zachoval obsah notebooku i všech sedmi Python souborů.

## Práce s notebookem

Notebook očekává složku `strategies/` relativně k pracovnímu adresáři `cviceni/`. Používá Python, NumPy a Matplotlib; původní postup je popsaný pro Colab. Prostředí při importu nebylo instalováno ani testováno.

Buňky pro přípravu a generování strategií zapisují soubory do `strategies/`. Při pozdější práci mohou přepsat samostatně upravené moduly. Podrobnosti jsou v [místních pokynech](../AGENTS.md).

## Odevzdání podle zadání

Notebook požaduje odevzdat vyplněný soubor `.ipynb` a vlastní modul `strategies/moje_strategie.py` do univerzitního informačního systému. Termín ani skutečné odevzdání nejsou z dodaných podkladů doloženy.

## Původ

Podklady jsou uložené ve [složce cvičení](./). Notebook byl zkopírován do této složky, Python moduly do `strategies/` podle importů v notebooku. Originály zůstaly zachované; toto shrnutí je nová dokumentace.
