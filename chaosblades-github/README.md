# Blades of Chaos (Fabric 1.20.1)

Requisitos: JDK 17, Gradle 8.3+ (o el wrapper del template oficial de Fabric).

## Compilar
    gradle build
El .jar queda en build/libs/chaosblades-1.0.0.jar -> copialo a la carpeta mods
(junto con Fabric API 0.92.2+1.20.1).

## Probar en desarrollo
    gradle runClient

## Que hace
- Item "Blades of Chaos" (pestana Combate, o /give @s chaosblades:blades_of_chaos)
- Dano 9, velocidad de ataque 2.0, 2500 de durabilidad, a prueba de fuego/lava
- Cada golpe prende fuego al enemigo (6 s)
- Click derecho: impulso hacia adelante (cooldown 2 s, cuesta durabilidad)
- Receta: 4 lingotes de netherite, 1 cadena, 2 blaze rods
