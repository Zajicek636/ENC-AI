# Audit konzistence formátu výstupu: Refaktoring promptu

## 1. Strukturovaná metadata

| Pole | Hodnota |
|---|---|
| Název dokumentu | Pøíklad projektové dokumentace – Refaktoring promptu |
| Cíl | Porovnat nestrukturovaný a optimalizovaný prompt pro extrakci technických požadavkù, rizik a termínù z projektové dokumentace |
| Testovací dokument | Návrh projektu zavedení interního informaèního systému |
| Poèet opakování | 5 na každý prompt |
| Teplota | T = 0,2 |
| API model | Stejný pro oba prompty (konkrétní model není uveden) |
| Konkrétní termíny / data | Ve zdrojovém textu nejsou uvedeny žádná kalendáøní data ani termíny |

### Prvky optimalizovaného promptu

- Oddìlené instrukce
- XML znaèky
- Definovaná role modelu
- Omezení
- Požadovaná struktura JSON

## 2. Porovnání konzistence výstupu bìhem 5 opakování

| Prompt | Validní JSON | Úspìšnost |
|---|---|---|
| Nestrukturovaný (pùvodní) | 3 z 5 bìhù | 60 % |
| Optimalizovaný (refaktorovaný) | 5 z 5 bìhù | 100 % |

### Zaznamenané chyby pùvodního promptu

- Doplòující komentáøe mimo JSON
- Nekonzistentní struktura položek s termíny

### Závìr dokumentu

Refaktorovaný prompt poskytl stabilnìjší a pøedvídatelnìjší výstup. Díky explicitním omezením lépe zabránil interpretaci nebo domýšlení údajù, které nebyly ve vstupním dokumentu uvedeny.

> Poznámka k pøesnosti: Zdroj uvádí pouze souhrnné výsledky. Údaje o tom, které konkrétní bìhy selhaly, a jaká chyba se vyskytla v kterém bìhu, v textu nejsou.

## 3. Jak explicitní XML tagy zabraòují nechtìnému spuštìní instrukcí

1. **Oddìlení instrukcí od dat.** Tagy jako `<instructions>` a `<document>` jasnì vymezují, co je zadání a co zpracovávaný obsah. Imperativní vìta uvnitø dokumentu (napø. „ignoruj pøedchozí pokyny“) je pak model schopen chápat jako data k analýze, nikoli jako pøíkaz.
2. **Jednoznaèné hranice.** Pevné otevírací a zavírací znaèky odstraòují nejednoznaènost, kde zadání konèí a kde zaèíná vstupní text. Nestrukturovaný prompt tyto hranice nemá, proto se instrukce a obsah mohou mísit.
3. **Urèení rolí jednotlivých èástí.** Samostatné bloky (`<role>`, `<constraints>`, `<output_format>`) dávají každé èásti jasný úèel. Model tak spolehlivìji dodržuje omezení, napøíklad zákaz textu mimo JSON.
4. **Lepší kontrola výstupu.** Požadovanou strukturu lze pøedepsat výslovnì, což odpovídá nulovému výskytu komentáøù mimo JSON v optimalizovaných bìzích.

> Omezení: XML tagy riziko snižují, ale nezaruèují úplnou ochranu pøed prompt injection. Pro kritické aplikace je vhodné je kombinovat s validací výstupu a pøípadnì s filtrací vstupu.

---
title: Perturbaèní test – posun distribuce labelù a analýza zkreslení poøadím (Recency Bias)
metodika: Stanford CS221 (perturbaèní / invariance test)
stav: KOMPLETNÍ – k dispozici všechny 3 výstupy (A, B1, B2)
jazyk: cs
---

# 1. Stav vstupních dat

| ID | Varianta | Popis | Dostupnost |
|---|---|---|---|
| A | `no-struct` | Nestrukturovaný prompt (baseline) | ? k dispozici, **ale neklasifikuje 10 komentáøù** (viz 2) |
| B1 | `struct` è. 1 | Strukturovaný výstup, 10 položek | ? |
| B2 | `struct` è. 2 | Strukturovaný výstup, 9 položek | ? |

**Dùležité omezení:** vstupy B1 a B2 mají **stejné poøadí** komentáøù (1›10, resp. 1›9). Nejde tedy o permutaci poøadí, ale o **replikaci/perturbaci výstupu pøi stejném vstupu** (stochastická variabilita generování). Recency Bias lze proto hodnotit jen nepøímo.

# 2. Metadata variant

## 2.1 Varianta A (nestrukturovaný)

- Odpovìï se vìnuje chybné úloze (klasifikace vìty „stack overflow if n > 10000“, label `CHANGES_REQUESTED`), nikoli 10 èeským komentáøùm.
- Použitá sada labelù: **vlastní, neomezená** (`APPROVED`, `CHANGES_REQUESTED`, `NEEDS_CLARIFICATION`, `SUGGESTION`), není shodná s povolenou sadou `[APPROVED, CHANGES_REQUESTED, COMMENT]`.
- Pozice 1–10 jsou pouze **orientaèní odhad** („likely“), seskupené do blokù, ne finální klasifikace.
- Formát `review` › `thought` › `label`: **nedodržen**.
- Jazyk odpovìdi: angliètina (vstup èesky).

| Skupina pozic | Label (A) | Poznámka |
|---|---|---|
| 3, 4, 5, 6 | CHANGES_REQUESTED | jistá chyba pøekladu / bìhu |
| 1, 2, 7, 8 | CHANGES_REQUESTED nebo NEEDS_CLARIFICATION | nerozhodnuto |
| 9, 10 | APPROVED / SUGGESTION | nerozhodnuto |

## 2.2 Varianty B1 a B2

| Vlastnost | B1 | B2 |
|---|---|---|
| Poèet klasifikovaných položek | 10 | 9 (deklarováno „devíti“), ve skuteènosti 10 blokù |
| Poèet blokù v tìle | 10 | 10 |
| Chyba poètu v úvodu | ne | **ano** („všech devíti“, správnì 10) |
| Formát `review/thought/label` | ? | ? |
| Povolená sada labelù | ? 10/10 | ? 10/10 |
| Úvodní vìta | ano | ano |

# 3. Klasifikace po položkách

| Poz. | Téma | A (odhad) | B1 | B2 | Shoda B1=B2 |
|---|---|---|---|---|---|
| 1 | Chybìjící `using` pro `Button` | CR / NC | COMMENT | COMMENT | ? |
| 2 | `OnClick =` vs. `+=` | CR / NC | COMMENT | COMMENT | ? |
| 3 | Chybí støedník + argument | CR | CHANGES_REQUESTED | CHANGES_REQUESTED | ? |
| 4 | `LoadData(string data)` povinný parametr | CR | **COMMENT** | **CHANGES_REQUESTED** | ? **FLIP** |
| 5 | `int number = "10";` | CR | CHANGES_REQUESTED | CHANGES_REQUESTED | ? |
| 6 | `null.Length` | CR | CHANGES_REQUESTED | CHANGES_REQUESTED | ? |
| 7 | `SaveData()` placeholder | CR / NC | COMMENT | COMMENT | ? |
| 8 | `ClickButton()` volá `LoadData()` | CR / NC | COMMENT | COMMENT | ? |
| 9 | Pojmenování metod | APPROVED / SUGG. | APPROVED | APPROVED | ? |
| 10 | Celková struktura | APPROVED / SUGG. | COMMENT | COMMENT | ? |

Zkratky: CR = CHANGES_REQUESTED, NC = NEEDS_CLARIFICATION.

# 4. Distribuce labelù

| Label | B1 poèet | B1 podíl | B2 poèet | B2 podíl | ? (B2-B1) |
|---|---|---|---|---|---|
| COMMENT | 6 | 60 % | 5 | 50 % | -10 p. b. |
| CHANGES_REQUESTED | 3 | 30 % | 4 | 40 % | +10 p. b. |
| APPROVED | 1 | 10 % | 1 | 10 % | 0 |

# 5. Metriky perturbaèního testu (B1 vs. B2)

| Metrika | Hodnota | Výpoèet / poznámka |
|---|---|---|
| Poèet položek | 10 | |
| Poèet flipù | **1** | pozice 4 |
| Flip rate | **10 %** | 1/10 |
| Pozorovaná shoda (p_o) | 0,90 | 9/10 |
| Cohenovo ? | **? 0,83** | p_e = 0,6·0,5 + 0,3·0,4 + 0,1·0,1 = 0,43; ? = (0,90 - 0,43)/(1 - 0,43) ? 0,82 |
| JS divergence (distribuce) | **? 0,003 bitù** | velmi nízká, distribuce témìø shodné |
| Flip na pozicích 1–3 | 0/3 = 0 % | |
| Flip na pozicích 4–7 | 1/4 = 25 % | støed seznamu |
| Flip na pozicích 8–10 | 0/3 = 0 % | |

**Interpretace:** ? ? 0,8 znaèí velmi dobrou, ne dokonalou shodu. Rozdíl distribucí je malý (±1 položka) a odpovídá náhodnému šumu generování.

# 6. Analýza flipu na pozici 4

- Text komentáøe je **vnitønì smíšený**: faktická chyba (volání bez argumentu se nepøeloží) + nejistota o zámìru autora („nevíme, zda…“).
- B1 zvolil na základì nejistoty zámìru › `COMMENT`.
- B2 zvolil na základì skuteèné chyby pøekladu › `CHANGES_REQUESTED`.
- Jde o **hranièní pøípad** (vysoká vnitøní nejednoznaènost zadání), ne o systematický posun.
- Navazující pozice 3 (`CHANGES_REQUESTED`) a 4 mají v textu shodnou pøíèinu (chybìjící argument u `LoadData`). **B1 je tak vnitønì ménì konzistentní** (pozice 3 › CR, pozice 4 › COMMENT, pøestože oba komentáøe popisují týž nepøeložitelný kód).

# 7. Analýza zkreslení poøadím (Recency / Primacy)

| Hypotéza | Pozorování | Závìr |
|---|---|---|
| Recency Bias (posun k poslední položce) | Flip na pozici 4, nikoli v posledních pozicích; pozice 8–10 stabilní | **Nepotvrzeno** |
| Primacy Bias | Pozice 1–3 stabilní | **Nepotvrzeno** |
| Posun distribuce | ±10 p. b. u COMMENT / CR | **Nevýznamný** (1 položka z 10) |
| Dopad délky seznamu (9 vs. 10) | B2 uvádí chybnì „devíti“, ale klasifikuje všech 10 | Vada **poèítání**, ne klasifikace |

**Omezení závìru:**
1. B1 a B2 mají **totožné poøadí**; skuteèná poziènì-invariantní analýza (reverze 10›1, náhodná permutace) **nebyla provedena**.
2. n = 10 je pro statistickou významnost pøíliš malé; jeden flip mìní flip rate o 10 p. b.
3. Pozorovaná variabilita je **stochasticky podmínìná**, ne prokazatelnì poziènì podmínìná.
4. Konfundace: komentáøe 9–10 jsou obsahovì mírnìjší než 3–6, takže jejich label nelze pøièíst poøadí.

# 8. Srovnání A vs. B (strukturované vs. nestrukturované)

| Kritérium | A (no-struct) | B1 | B2 |
|---|---|---|---|
| Øeší správnou úlohu | ? | ? | ?
| Dodržení sady labelù | ? (4 vlastní) | ? | ?
| Dodržení formátu ukázek | ? | ? | ?
| Konkrétní label pro každou položku | ? (skupiny, alternativy) | ? | ?
| Zdùvodnìní per položka (`thought`) | ? | ? | ?
| Jazyková shoda se vstupem | ? | ? | ?
| Strojovì zpracovatelný výstup | ? | ? | ?
| Stabilita (flip rate) | nelze mìøit | 10 % vùèi B2 | 10 % vùèi B1 |

**Shoda A s B (hrubì, pouze u jednoznaèných skupin):**
- Pozice 3, 5, 6: A = CR, B1 = B2 = CR ?
- Pozice 1, 2, 7, 8: A „CR nebo NC“; B má `COMMENT` (tøída `COMMENT` odpovídá spíše „NC/SUGGESTION“) › èásteèná shoda, nelze exaktnì porovnat kvùli rozdílné sadì labelù.
- Pozice 9: A „APPROVED/SUGGESTION“, B = APPROVED ?
- Pozice 10: A „APPROVED/SUGGESTION“, B = COMMENT › èásteèná shoda.

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
  - "A: øeší jinou úlohu a používá jinou sadu labelù"
  - "B2: chybné tvrzení o poètu komentáøù (9 místo 10)"
  - "B1 vs. B2: vnitøní nekonzistence na pozici 3 vs. 4 (B1)"
```

# 10. Doporuèení

1. **Skuteèný test pozièního zkreslení:** spustit P1 (reverze 10›1) a P2 (? 3 náhodné permutace se zaznamenaným seedem), každou ? 5× kvùli oddìlení pozièního efektu od stochastického šumu.
2. **Izolovaná klasifikace (P3):** 1 komentáø / 1 volání jako referenèní „ground truth“ bez kontextového vlivu.
3. **Rozšíøit vzorek** na ? 50 komentáøù pro smysluplný interval spolehlivosti flip rate.
4. **Upøesnit zadání:** doplnit do instrukcí pravidlo pro hranièní pøípady (napø. „jistá chyba pøekladu › `CHANGES_REQUESTED`, i když je nejistý zámìr autora“), èímž se odstraní flip na pozici 4.
5. **Ovìøovat poèet výstupù:** pøidat do promptu požadavek `poèet vstupù = poèet výstupních blokù`, aby se eliminovala chyba „devíti“ vs. 10.
6. **Pro variantu A** vždy uvádìt povolenou sadu labelù i formát, aby byl výstup srovnatelný s B.