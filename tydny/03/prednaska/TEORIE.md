# Strukturované prompty a konzistence výstupů

**Původ:** studijní výtah z [výsledkového dokumentu cvičení 3](../cviceni/res-xdvora20-utf8.md), vytvořený před dodáním přednášek. Dodaná přednáška 03 se věnuje agentům a prostředí; tento text zůstává výtahem ze cvičení o promptech. Úplné prompty a jednotlivé experimentální záznamy nebyly dodány. Doplňující vysvětlení a kontrolní výpočty jsou označeny; původní obsah zůstává zachovaný.

[Shrnutí složky](SHRNUTI.md) · [Přehled týdne](../README.md)

## Struktura promptu a oddělení dat

Podklad porovnává volné zadání se strukturovaným promptem, který odděluje instrukce, roli modelu, omezení, vstupní dokument a požadovaný výstup. XML značky jako `instructions`, `document` a `output_format` pomáhají jednotlivé části rozlišit.

Při extrakci údajů je důležité určit, co lze převzít ze zdroje a jak zacházet s chybějící informací. Pokud dokument neuvádí termín, model jej nemá domýšlet. Konkrétní reprezentaci chybějící hodnoty musí určit výstupní kontrakt; dodaný souhrn jeho úplnou podobu neobsahuje.

**Doplňující vysvětlení:** značky vytvářejí textové hranice, nikoli bezpečnostní izolaci. Imperativní věta ve zpracovávaném dokumentu zůstává daty k analýze. Samotné XML nezaručuje ochranu před prompt injection ani správné plnění zadání.

## Validní JSON a správnost obsahu

První část zdroje deklaruje pět běhů pro každý prompt při teplotě 0,2. Původní prompt vytvořil validní JSON ve třech případech, upravený v pěti. Mezi popsanými problémy byly komentáře mimo JSON a nekonzistentní položky termínů.

**Doplňující vysvětlení:** hodnocení výstupu má několik odlišných úrovní:

| Úroveň | Co ověřuje |
| --- | --- |
| Syntaktická validita | Zda lze celý výstup načíst jako JSON |
| Shoda se schématem | Zda obsahuje očekávaná pole, typy, povolené hodnoty a počet položek |
| Věcná správnost | Zda údaje odpovídají zdroji a nejsou domyšlené |
| Úplnost | Zda výstup zahrnuje vše požadované zadáním |

Validní JSON proto nemusí znamenat správnou extrakci. Pět úspěšných běhů dokládá výsledek na daném malém vzorku, nikoli zaručenou budoucí úspěšnost. Bez původních výstupů zde nelze nezávisle provést ani syntaktickou kontrolu.

## Konzistence klasifikací

Druhá část porovnává dvě strukturované klasifikace B1/B2 deseti komentářů. Labely jsou `COMMENT`, `CHANGES_REQUESTED` a `APPROVED`. V tabulce se liší jediná položka, na pozici 4.

Flip rate je podíl položek, u kterých se label mezi běhy změnil. Zde vychází `1/10 = 10 %`. Pozorovaná shoda je `9/10 = 90 %`.

**Doplňující vysvětlení:** shoda měří konzistenci, nikoli správnost. Dva běhy se mohou shodnout na nesprávném labelu. Referenční hodnocení vyžaduje doložená kritéria a ověřené odpovědi; samostatné volání modelu pro jeden komentář není samo o sobě referenční pravdou.

Varianta A není pro stejnou metriku přímo použitelná: podle zdroje řeší jinou úlohu a používá odlišné labely a skupinové odhady. Její porovnání s B by vyžadovalo srovnatelné vstupy, úlohu a výstupní kontrakt.

## Cohenovo κ

Cohenovo κ upravuje pozorovanou shodu o očekávanou shodu vypočtenou z marginálních četností labelů:

`κ = (p_o − p_e) / (1 − p_e)`.

**Kontrolní výpočet z dodané tabulky:** B1 má četnosti `(6, 3, 1)`, B2 `(5, 4, 1)` v pořadí COMMENT, CHANGES_REQUESTED, APPROVED. Proto:

- `p_o = 0,90`;
- `p_e = 0,6 × 0,5 + 0,3 × 0,4 + 0,1 × 0,1 = 0,43`;
- `κ = (0,90 − 0,43) / (1 − 0,43) ≈ 0,82456`.

Při zaokrouhlení na dvě desetinná místa jde o 0,82. Zdroj uvádí také 0,83, což tomuto zaokrouhlení neodpovídá. Přepočet je kontrolou uvedené tabulky, nikoli nezávislým ověřením původních běhů.

**Doplňující vysvětlení:** κ závisí také na zastoupení tříd. Samotné číslo není důkaz kvality klasifikace a jeho interpretace má zohlednit úlohu, četnosti a velikost vzorku.

## Jensenova–Shannonova divergence

JS divergence porovnává celkové distribuce labelů. Pro distribuce `P`, `Q` a jejich průměr `M = (P + Q) / 2` platí:

`JS(P, Q) = ½ KL(P || M) + ½ KL(Q || M)`,

kde při logaritmu o základu 2 je `KL(P || M) = Σ p_i log₂(p_i / m_i)` a výsledek má jednotku bit.

**Kontrolní výpočet:** pro `P = (0,6; 0,3; 0,1)`, `Q = (0,5; 0,4; 0,1)` a `M = (0,55; 0,35; 0,1)` vychází JS divergence přibližně **0,00845 bitu**. Zdroj uvádí 0,003 bitu, což těmto distribucím neodpovídá. Jde o divergenci, nikoli její odmocninu.

**Doplňující vysvětlení:** malá změna distribuce nemusí znamenat malý počet změn jednotlivých položek. Labely se mohou mezi položkami prohodit a četnosti zůstanou stejné. Proto je vhodné rozlišovat distribuci labelů od shody konkrétních klasifikací.

## Testování vlivu pořadí

Podle podkladu mají B1 a B2 stejné pořadí. Pozorovaná změna tedy sama o sobě netestuje citlivost na přesun položky. Stabilita prvních a posledních pozic neprokazuje absenci primacy nebo recency bias.

**Doplňující vysvětlení:** při testování pořadí je potřeba zachovat obsah a identitu položek, změnit jejich pozice a výsledky porovnat podle identity, nikoli jen čísla řádku. Lze použít obrácené pořadí a náhodné permutace, opakovat běhy a evidovat model, parametry i použité permutace. Tak lze lépe oddělit vliv pořadí od variability opakovaných výstupů.

U deseti položek mění jediný flip výsledek o deset procentních bodů. Bez vhodného statistického vyhodnocení nelze rozdíl označit za statisticky nevýznamný ani jej s jistotou připsat náhodě. Příčina odlišného labelu ve zdroji je interpretace, nikoli přímé pozorování rozhodování modelu.

Doporučené nové experimenty ve zdroji nebyly při importu provedeny. Upřesnění hraničních případů a kontrola počtu výstupů mohou omezit chyby, ale prompt sám nezaručuje jejich odstranění.

## Otázky pro zopakování

1. Jaký je rozdíl mezi validním JSON, shodou se schématem a věcnou správností?
2. K čemu pomáhají XML oddělovače a co samy nezaručují?
3. Co měří flip rate a co pozorovaná shoda?
4. Jak vznikne očekávaná shoda 0,43 v uvedeném výpočtu κ?
5. Mohou mít dvě klasifikace stejnou distribuci labelů a přesto se lišit u mnoha položek?
6. Proč porovnání dvou běhů se stejným pořadím neověřuje vliv permutace?
7. Proč nelze izolovaný výstup modelu automaticky označit za správnou referenční odpověď?

Otázky jsou doplněná studijní pomůcka; nejde o dodané zkouškové otázky.
