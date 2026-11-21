# Audit konzistence formátu výstupu: Refaktoring promptu

## 1. Strukturovaná metadata

| Pole | Hodnota |
|---|---|
| Název dokumentu | Příklad projektové dokumentace – Refaktoring promptu |
| Cíl | Porovnat nestrukturovaný a optimalizovaný prompt pro extrakci technických požadavků, rizik a termínů z projektové dokumentace |
| Testovací dokument | Návrh projektu zavedení interního informačního systému |
| Počet opakování | 5 na každý prompt |
| Teplota | T = 0,2 |
| API model | Stejný pro oba prompty (konkrétní model není uveden) |
| Konkrétní termíny / data | Ve zdrojovém textu nejsou uvedeny žádná kalendářní data ani termíny |

### Prvky optimalizovaného promptu

- Oddělené instrukce
- XML značky
- Definovaná role modelu
- Omezení
- Požadovaná struktura JSON

## 2. Porovnání konzistence výstupu během 5 opakování

| Prompt | Validní JSON | Úspěšnost |
|---|---|---|
| Nestrukturovaný (původní) | 3 z 5 běhů | 60 % |
| Optimalizovaný (refaktorovaný) | 5 z 5 běhů | 100 % |

### Zaznamenané chyby původního promptu

- Doplňující komentáře mimo JSON
- Nekonzistentní struktura položek s termíny

### Závěr dokumentu

Refaktorovaný prompt poskytl stabilnější a předvídatelnější výstup. Díky explicitním omezením lépe zabránil interpretaci nebo domýšlení údajů, které nebyly ve vstupním dokumentu uvedeny.

> Poznámka k přesnosti: Zdroj uvádí pouze souhrnné výsledky. Údaje o tom, které konkrétní běhy selhaly, a jaká chyba se vyskytla v kterém běhu, v textu nejsou.

## 3. Jak explicitní XML tagy zabraňují nechtěnému spuštění instrukcí

1. **Oddělení instrukcí od dat.** Tagy jako `<instructions>` a `<document>` jasně vymezují, co je zadání a co zpracovávaný obsah. Imperativní věta uvnitř dokumentu (např. „ignoruj předchozí pokyny“) je pak model schopen chápat jako data k analýze, nikoli jako příkaz.
2. **Jednoznačné hranice.** Pevné otevírací a zavírací značky odstraňují nejednoznačnost, kde zadání končí a kde začíná vstupní text. Nestrukturovaný prompt tyto hranice nemá, proto se instrukce a obsah mohou mísit.
3. **Určení rolí jednotlivých částí.** Samostatné bloky (`<role>`, `<constraints>`, `<output_format>`) dávají každé části jasný účel. Model tak spolehlivěji dodržuje omezení, například zákaz textu mimo JSON.
4. **Lepší kontrola výstupu.** Požadovanou strukturu lze předepsat výslovně, což odpovídá nulovému výskytu komentářů mimo JSON v optimalizovaných bězích.

> Omezení: XML tagy riziko snižují, ale nezaručují úplnou ochranu před prompt injection. Pro kritické aplikace je vhodné je kombinovat s validací výstupu a případně s filtrací vstupu.

---
title: Perturbační test – posun distribuce labelů a analýza zkreslení pořadím (Recency Bias)
metodika: Stanford CS221 (perturbační / invariance test)
stav: KOMPLETNÍ – k dispozici všechny 3 výstupy (A, B1, B2)
jazyk: cs
---

# 1. Stav vstupních dat

| ID | Varianta | Popis | Dostupnost |
|---|---|---|---|
| A | `no-struct` | Nestrukturovaný prompt (baseline) | ? k dispozici, **ale neklasifikuje 10 komentářů** (viz 2) |
| B1 | `struct` č. 1 | Strukturovaný výstup, 10 položek | ? |
| B2 | `struct` č. 2 | Strukturovaný výstup, 9 položek | ? |

**Důležité omezení:** vstupy B1 a B2 mají **stejné pořadí** komentářů (1›10, resp. 1›9). Nejde tedy o permutaci pořadí, ale o **replikaci/perturbaci výstupu při stejném vstupu** (stochastická variabilita generování). Recency Bias lze proto hodnotit jen nepřímo.

# 2. Metadata variant

## 2.1 Varianta A (nestrukturovaný)

- Odpověď se věnuje chybné úloze (klasifikace věty „stack overflow if n > 10000“, label `CHANGES_REQUESTED`), nikoli 10 českým komentářům.
- Použitá sada labelů: **vlastní, neomezená** (`APPROVED`, `CHANGES_REQUESTED`, `NEEDS_CLARIFICATION`, `SUGGESTION`), není shodná s povolenou sadou `[APPROVED, CHANGES_REQUESTED, COMMENT]`.
- Pozice 1–10 jsou pouze **orientační odhad** („likely“), seskupené do bloků, ne finální klasifikace.
- Formát `review` › `thought` › `label`: **nedodržen**.
- Jazyk odpovědi: angličtina (vstup česky).

| Skupina pozic | Label (A) | Poznámka |
|---|---|---|
| 3, 4, 5, 6 | CHANGES_REQUESTED | jistá chyba překladu / běhu |
| 1, 2, 7, 8 | CHANGES_REQUESTED nebo NEEDS_CLARIFICATION | nerozhodnuto |
| 9, 10 | APPROVED / SUGGESTION | nerozhodnuto |

## 2.2 Varianty B1 a B2

| Vlastnost | B1 | B2 |
|---|---|---|
| Počet klasifikovaných položek | 10 | 9 (deklarováno „devíti“), ve skutečnosti 10 bloků |
| Počet bloků v těle | 10 | 10 |
| Chyba počtu v úvodu | ne | **ano** („všech devíti“, správně 10) |
| Formát `review/thought/label` | ? | ? |
| Povolená sada labelů | ? 10/10 | ? 10/10 |
| Úvodní věta | ano | ano |

# 3. Klasifikace po položkách

| Poz. | Téma | A (odhad) | B1 | B2 | Shoda B1=B2 |
|---|---|---|---|---|---|
| 1 | Chybějící `using` pro `Button` | CR / NC | COMMENT | COMMENT | ? |
| 2 | `OnClick =` vs. `+=` | CR / NC | COMMENT | COMMENT | ? |
| 3 | Chybí středník + argument | CR | CHANGES_REQUESTED | CHANGES_REQUESTED | ? |
| 4 | `LoadData(string data)` povinný parametr | CR | **COMMENT** | **CHANGES_REQUESTED** | ? **FLIP** |
| 5 | `int number = "10";` | CR | CHANGES_REQUESTED | CHANGES_REQUESTED | ? |
| 6 | `null.Length` | CR | CHANGES_REQUESTED | CHANGES_REQUESTED | ? |
| 7 | `SaveData()` placeholder | CR / NC | COMMENT | COMMENT | ? |
| 8 | `ClickButton()` volá `LoadData()` | CR / NC | COMMENT | COMMENT | ? |
| 9 | Pojmenování metod | APPROVED / SUGG. | APPROVED | APPROVED | ? |
| 10 | Celková struktura | APPROVED / SUGG. | COMMENT | COMMENT | ? |

Zkratky: CR = CHANGES_REQUESTED, NC = NEEDS_CLARIFICATION.

# 4. Distribuce labelů

| Label | B1 počet | B1 podíl | B2 počet | B2 podíl | ? (B2-B1) |
|---|---|---|---|---|---|
| COMMENT | 6 | 60 % | 5 | 50 % | -10 p. b. |
| CHANGES_REQUESTED | 3 | 30 % | 4 | 40 % | +10 p. b. |
| APPROVED | 1 | 10 % | 1 | 10 % | 0 |

# 5. Metriky perturbačního testu (B1 vs. B2)

| Metrika | Hodnota | Výpočet / poznámka |
|---|---|---|
| Počet položek | 10 | |
| Počet flipů | **1** | pozice 4 |
| Flip rate | **10 %** | 1/10 |
| Pozorovaná shoda (p_o) | 0,90 | 9/10 |
| Cohenovo ? | **? 0,83** | p_e = 0,6·0,5 + 0,3·0,4 + 0,1·0,1 = 0,43; ? = (0,90 - 0,43)/(1 - 0,43) ? 0,82 |
| JS divergence (distribuce) | **? 0,003 bitů** | velmi nízká, distribuce téměř shodné |
| Flip na pozicích 1–3 | 0/3 = 0 % | |
| Flip na pozicích 4–7 | 1/4 = 25 % | střed seznamu |
| Flip na pozicích 8–10 | 0/3 = 0 % | |

**Interpretace:** ? ? 0,8 značí velmi dobrou, ne dokonalou shodu. Rozdíl distribucí je malý (±1 položka) a odpovídá náhodnému šumu generování.

# 6. Analýza flipu na pozici 4

- Text komentáře je **vnitřně smíšený**: faktická chyba (volání bez argumentu se nepřeloží) + nejistota o záměru autora („nevíme, zda…“).
- B1 zvolil na základě nejistoty záměru › `COMMENT`.
- B2 zvolil na základě skutečné chyby překladu › `CHANGES_REQUESTED`.
- Jde o **hraniční případ** (vysoká vnitřní nejednoznačnost zadání), ne o systematický posun.
- Navazující pozice 3 (`CHANGES_REQUESTED`) a 4 mají v textu shodnou příčinu (chybějící argument u `LoadData`). **B1 je tak vnitřně méně konzistentní** (pozice 3 › CR, pozice 4 › COMMENT, přestože oba komentáře popisují týž nepřeložitelný kód).

# 7. Analýza zkreslení pořadím (Recency / Primacy)

| Hypotéza | Pozorování | Závěr |
|---|---|---|
| Recency Bias (posun k poslední položce) | Flip na pozici 4, nikoli v posledních pozicích; pozice 8–10 stabilní | **Nepotvrzeno** |
| Primacy Bias | Pozice 1–3 stabilní | **Nepotvrzeno** |
| Posun distribuce | ±10 p. b. u COMMENT / CR | **Nevýznamný** (1 položka z 10) |
| Dopad délky seznamu (9 vs. 10) | B2 uvádí chybně „devíti“, ale klasifikuje všech 10 | Vada **počítání**, ne klasifikace |

**Omezení závěru:**
1. B1 a B2 mají **totožné pořadí**; skutečná pozičně-invariantní analýza (reverze 10›1, náhodná permutace) **nebyla provedena**.
2. n = 10 je pro statistickou významnost příliš malé; jeden flip mění flip rate o 10 p. b.
3. Pozorovaná variabilita je **stochasticky podmíněná**, ne prokazatelně pozičně podmíněná.
4. Konfundace: komentáře 9–10 jsou obsahově mírnější než 3–6, takže jejich label nelze přičíst pořadí.

# 8. Srovnání A vs. B (strukturované vs. nestrukturované)

| Kritérium | A (no-struct) | B1 | B2 |
|---|---|---|---|
| Řeší správnou úlohu | ? | ? | ?
| Dodržení sady labelů | ? (4 vlastní) | ? | ?
| Dodržení formátu ukázek | ? | ? | ?
| Konkrétní label pro každou položku | ? (skupiny, alternativy) | ? | ?
| Zdůvodnění per položka (`thought`) | ? | ? | ?
| Jazyková shoda se vstupem | ? | ? | ?
| Strojově zpracovatelný výstup | ? | ? | ?
| Stabilita (flip rate) | nelze měřit | 10 % vůči B2 | 10 % vůči B1 |

**Shoda A s B (hrubě, pouze u jednoznačných skupin):**
- Pozice 3, 5, 6: A = CR, B1 = B2 = CR ?
- Pozice 1, 2, 7, 8: A „CR nebo NC“; B má `COMMENT` (třída `COMMENT` odpovídá spíše „NC/SUGGESTION“) › částečná shoda, nelze exaktně porovnat kvůli rozdílné sadě labelů.
- Pozice 9: A „APPROVED/SUGGESTION“, B = APPROVED ?
- Pozice 10: A „APPROVED/SUGGESTION“, B = COMMENT › částečná shoda.

# 9. Souhrn metadat

```yaml
test: perturbation_invariance
dataset_size: 10
variants:
  A:  {structured: false, label_set_valid: false, per_item_labels: partial}
  B1: {structured: true,  label_set_valid: true,  per_item_labels: 10}
  B2: {structured: true,  label_set_valid: true,  per_item_labels: 10, count_error_in_intro: true}
b1_vs_b2:
  flips: 1
  flip_positions: [4]
  flip_rate: 0.10
  cohens_kappa: 0.82
  js_divergence_bits: 0.003
  distribution_shift_max_pp: 10
order_bias:
  recency_bias_detected: false
  primacy_bias_detected: false
  permutation_test_performed: false
  statistical_power: low (n=10)
data_quality_issues:
  - "A: řeší jinou úlohu a používá jinou sadu labelů"
  - "B2: chybné tvrzení o počtu komentářů (9 místo 10)"
  - "B1 vs. B2: vnitřní nekonzistence na pozici 3 vs. 4 (B1)"
```

# 10. Doporučení

1. **Skutečný test pozičního zkreslení:** spustit P1 (reverze 10›1) a P2 (? 3 náhodné permutace se zaznamenaným seedem), každou ? 5× kvůli oddělení pozičního efektu od stochastického šumu.
2. **Izolovaná klasifikace (P3):** 1 komentář / 1 volání jako referenční „ground truth“ bez kontextového vlivu.
3. **Rozšířit vzorek** na ? 50 komentářů pro smysluplný interval spolehlivosti flip rate.
4. **Upřesnit zadání:** doplnit do instrukcí pravidlo pro hraniční případy (např. „jistá chyba překladu › `CHANGES_REQUESTED`, i když je nejistý záměr autora“), čímž se odstraní flip na pozici 4.
5. **Ověřovat počet výstupů:** přidat do promptu požadavek `počet vstupů = počet výstupních bloků`, aby se eliminovala chyba „devíti“ vs. 10.
6. **Pro variantu A** vždy uvádět povolenou sadu labelů i formát, aby byl výstup srovnatelný s B.