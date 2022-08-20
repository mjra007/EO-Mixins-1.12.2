# EO Mixins 1.12.2

A bunch of mixins for EO 1.12.2 modpack. EO-mixins will be injected inside other mods to fix or change their natural behaviour.
 

## Mixins

**[Hats](https://www.curseforge.com/minecraft/mc-mods/hats) Mixin**

Forces Hats mods to register EntityHat as hats:hat. This is useful if you use the [EntityCulling](https://www.curseforge.com/minecraft/mc-mods/entity-culling) mod as it makes it possible to add it to the list of entities to ignore.
As a result when these two mods are present hats will no longer stop rendering randomly.