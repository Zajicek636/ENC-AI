# ENC-AI

Pracovní a informační prostor pro předmět ENC-AI: podklady z přednášek, zadání a řešení cvičení a příprava na zkoušku. Každý týden má samostatnou přednášku a cvičení.

## Rozcestník

- [Přehled týdnů](tydny/README.md)
- [Materiály ke zkoušce](zkouska/README.md)
- [Pravidla práce v projektu](AGENTS.md)
- [Skill pro zařazování materiálů](.agents/skills/enc-ai-materialy/SKILL.md)

| Týden | Téma | Přednáška | Cvičení |
| --- | --- | --- | --- |
| [06](tydny/06/README.md) | Iterované vězňovo dilema a evoluční strategie | [Teorie ze cvičení](tydny/06/prednaska/TEORIE.md); vlastní přednáška zatím nedodána | [Notebook, strategie a shrnutí](tydny/06/cviceni/SHRNUTI.md) |

Další týdny přibudou po dodání materiálů. Přítomnost řešení neznamená potvrzené odevzdání ani ověření správnosti.

## Organizace

```text
tydny/NN/
  AGENTS.md          místní pokyny pro práci s podklady
  README.md          přehled týdne
  prednaska/         přednáška nebo označený výtah teorie ze cvičení
    SHRNUTI.md      přehled obsahu a zdrojů
  cviceni/          zadání, notebooky, kód a další podklady
    SHRNUTI.md      přehled úkolů, souborů a známého stavu
zkouska/            studijní materiály a tematické přehledy
```

Číslo týdne zapisujeme alespoň dvěma číslicemi. Původní názvy dodaných souborů zachováváme; nové organizační složky pojmenováváme bez diakritiky. Kód jednotlivých cvičení může používat vlastní potřebnou strukturu, například `strategies/` u týdne 06.

## Přidání materiálů

Projektový skill je uložený v `.agents/skills/enc-ai-materialy/`. Příklad požadavku:

```text
Použij $enc-ai-materialy na složku C:\Users\Radek\Downloads\ai7.
Zařaď ji do týdne 07 jako cvičení a aktualizuj shrnutí i rozcestníky.
```

Lze zadat i více jednotlivých souborů nebo podklady ke zkoušce. Týden a druh materiálu stačí uvést, pokud nejsou jasné z obsahu. Pokud klient skill nenabízí, lze agenta odkázat přímo na jeho `SKILL.md`.

Skill standardně kopíruje podklady, zachovává originály a doplňuje dokumentaci. Pokud chybí přednáška, může teorii obsaženou ve cvičení shrnout do samostatného souboru bez úprav cvičení.
