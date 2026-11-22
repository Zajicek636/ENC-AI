# BDI a plány v AgentSpeaku — výtah ze cvičení 07

Výtah vychází z oddílů 1–4 [pracovního listu semináře 07](../cviceni/2026-ENC-AI-CZ_Seminar_07_BDI_Agenti.md). Samostatná přednáška pro tento týden zatím chybí.

## Co agent ví a čeho chce dosáhnout

AgentSpeak je jazyk pro zápis chování BDI agentů. Jason jej interpretuje a propojuje agenty se simulovaným prostředím. V modelu BDI zachycují *beliefs* přesvědčení agenta o světě, *desires* vyjadřují jeho cíle a *intentions* popisují, čemu se agent při plnění cílů právě věnuje.

V příkladu `cleaning-robots` poskytuje prostředí na mřížce 7 × 7 vjem `pos(R,X,Y)` o poloze robota a `garbage(R)` o přítomnosti odpadu na jeho políčku. Z přesvědčení o poloze může agent pravidlem odvodit, že se dva roboti nacházejí na stejném místě. Při vyhodnocení pravidla se proměnné vážou unifikací podobně jako v Prologu.

## Jak agent reaguje

Plán má spouštěcí událost, kontext a tělo. Událost `+garbage(r1)` oznamuje přidání přesvědčení o odpadu u robota `r1`. U události `+!ensure_pick(S)` jde o přijetí cíle. Kontext `garbage(r1)` omezuje použitelnost příslušného plánu na situaci, kdy je podle agenta na jeho políčku odpad.

Tělo plánu určuje jednotlivé kroky. Akci `pick(garb)` vykonává prostředí, zatímco `.print(...)` je interní akce Jasonu. V příkladu zpracovává požadavky robotů javová metoda `executeAction` a změny prostředí se promítají do vjemů prostřednictvím `updatePercepts()`. Konfigurace `mars.mas2j` spojuje třídu prostředí s agenty, kteří v něm působí.

**Upřesnění syntaxe k oddílu 4.2.** Tělo plánu začíná za symbolem `<-` a jednotlivé kroky v těle odděluje středník. Zápis `!g` v těle zadává dílčí cíl a `?p` klade dotaz. Plán určuje, jak na příslušnou událost reagovat.
