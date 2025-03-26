# 3D Grafická Scéna

Tento projekt implementuje zobrazení jednoduché 3D scény obsahující více objektů, možnost jejich transformace a pohybu kamery.

## Ovládání

### Pohyb kamery
- **Rozhlížení myší**: pohyb myší s držením levého tlačítka
- **Pohyb vpřed, vzad, vlevo, vpravo**: klávesy `W`, `A`, `S`, `D`

### Transformace těles
- **Posun (translace)**: šipky `←`, `→`, `↑`, `↓`
- **Rotace kolem os**:
    - Osa X: `X`
    - Osa Y: `Y`
    - Osa Z: `Z`
- **Zvětšení/zmenšení (zoom)**:
    - Zvětšení: `M`
    - Zmenšení: `N`

### Výběr aktivního tělesa
- `1` – Cube (výchozí)
- `2` – Cuboid
- `3` – Pyramid
- `4` – Bézierova plocha

_(Poznámka: Objekt se neoznačuje vizuálně.)_

### Projekce
- **Přepínání mezi pravoúhlou a perspektivní projekcí**: `P`

### Rasterizace a zobrazení
- **Přepínání mezi drátovým modelem a vyplněnými plochami**: `F`
- **Jednobarevné plochy**: `V`
- **Interpolace barev mezi vrcholy**: `B`

### Animace
- **Zapnutí/vypnutí animace vybraného tělesa**: `R`

