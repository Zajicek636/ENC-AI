# Shrnutí — Cvičení 4

[Přehled týdne](../README.md) · [Teorie ke cvičení](../prednaska/TEORIE.md)

## Podklad a zadání

Veškeré dodané materiály obsahuje [pracovní list semináře 4](xdvora20_-_Seminar_4_Multi-Agentni_Architektu.md). Tématem je návrh agentních smyček a víceagentní spolupráce pomocí systémových rolí a strukturovaných zpráv.

| Část | Požadovaný výstup | Stav podkladu |
| --- | --- | --- |
| Úkol 1.1 | ReAct prompt se třemi pseudonástroji, limitem čtyř kroků a detekcí opakovaných pozorování | Návrh vyplněn; obsahuje nesoulad role popsaný níže |
| Testovací simulace | Záznam scénáře nedostupné služby `AuthService` a logu `TIMEOUT` | Vyplněný dvoukrokový záznam; skutečná volání neproběhla |
| Úkol 1.2 | Prompt Security Auditora a protokol předávání zpráv v týmu | Návrh vyplněn včetně vstupního a výstupního formátu |
| Úkol 1.3 | Odpovědi o kontextu více agentů a generování mezikroků | Obě odpovědi vyplněny |
| Checklist a reflexe | Kontrola vyplnění a zhodnocení cvičení | Vyplněno |

Název pracovního listu zmiňuje také Multi-Model Routing a Agentic RAG; samostatné podrobné úkoly k těmto tématům v dodaném souboru nejsou.

## ReAct a simulovaný log

Zadání požaduje pseudonástroje `vyhledat_v_logu(pattern)`, `restartovat_sluzbu(service_name)` a `dokoncit(sprava)`. Každý krok má oddělit stručné zdůvodnění, akci a pozorování. Limit činí čtyři kroky; při shodě pozorování s předchozím krokem se nesmí opakovat právě provedená akce se stejnými argumenty.

V dodaném logu první krok přebírá uživatelem uvedený nález `TIMEOUT`. Výslovně uvádí, že vyhledání nebylo skutečně vykonáno; status `SUCCESS` zde popisuje nález záznamu, nikoli dostupnost služby.

Druhý krok připravuje zprávu `BLOCKED`: příčina není potvrzena, restart není povolen a další diagnostika není dostupná. Výstup uvádí, že pseudonástroj `dokoncit` není připojen, takže předání neproběhlo. Záznam nedokládá skutečný restart, předání zprávy ani praktické ověření detekce zacyklení.

## Spolupráce agentů

Úkol 1.2 předepisuje posloupnost Architect → Developer → Security Auditor. Auditor má vydat `APPROVED`, nebo `REJECTED` s návratem práce Developerovi. Dodaný prompt zahrnuje kontrakt implementace, odkazy na kód, bezpečnostní kontroly, výsledky testů a nálezy s důkazy a doporučeními.

**Nesoulad v podkladu:** úkol 1.1 žádá diagnostického agenta, jeho řešení však začíná rolí Architect v týmu Architect–Developer–Tester. V úkolu 1.2 je třetí rolí Security Auditor. Tyto role nelze bez dalšího považovat za shodné. Originální řešení nebylo při importu opravováno.

## Analytické odpovědi a stav

Odpovědi vysvětlují zanesení kontextu nepotřebnými informacemi (*context pollution*), rozptýlení pozornosti mezi informacemi (*attention dispersion*) a přínos omezeného strukturovaného předání. Druhá odpověď rozlišuje další generované tokeny a výpočetní kroky od vlastní kapacity modelu; samotná značka `<thought>` správnost nezaručuje.

Reflexe označuje sladění vstupů a výstupů mezi agenty za nejnáročnější část. Všechny položky checklistu jsou označené jako vyplněné. To nepotvrzuje správnost návrhů, skutečný běh nástrojů ani odevzdání. Při importu neprobíhalo testování promptů nebo implementace agentního systému.

## Odevzdání a původ

Checklist požaduje vyplněný ReAct prompt s logem, prompt auditora a analytické odpovědi. Termín, způsob odevzdání a potvrzení odevzdání nejsou doloženy.

Zdroj: [xdvora20_-_Seminar_4_Multi-Agentni_Architektu.md](xdvora20_-_Seminar_4_Multi-Agentni_Architektu.md). Pracovní list byl zkopírován beze změn; toto shrnutí je nová dokumentace. Externí ikona Notion zůstává odkazem ve zdrojovém dokumentu a nebyla stahována.
