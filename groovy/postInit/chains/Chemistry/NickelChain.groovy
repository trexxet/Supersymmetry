PRIMITIVEBLASTFURNACE = recipemap('primitive_blast_furnace')
EBF = recipemap('electric_blast_furnace')
ROASTER = recipemap('roaster')

class Combustible {
    String name
    String byproduct
    int amount_required
    int duration
    Combustible(name, amount_required, duration, byproduct = 'dustTinyDarkAsh') {
        this.name = name
        this.amount_required = amount_required
        this.duration = duration
        this.byproduct = byproduct
    }
}

def combustibles = [
        new Combustible('gemCoke', 1, 3, 'dustTinyAsh'),
        new Combustible('dustCoke', 1, 3, 'dustTinyAsh'),
        new Combustible('gemAnthracite', 1, 2, 'dustTinyAsh'),
        new Combustible('dustAnthracite', 1, 2, 'dustTinyAsh'),
        new Combustible('gemCoal', 2, 4),
        new Combustible('dustCoal', 2, 4),
        new Combustible('gemCharcoal', 2, 4),
        new Combustible('dustCharcoal', 2, 4)
]

for (combustible in combustibles) {
    PRIMITIVEBLASTFURNACE.recipeBuilder()
            .inputs(ore('dustNickel'))
            .inputs(ore(combustible.name) * (combustible.amount_required))
            .outputs(metaitem('ingotNickel'))
            .outputs(metaitem(combustible.byproduct) * (combustible.amount_required))
            .duration(200)
            .buildAndRegister()

    PRIMITIVEBLASTFURNACE.recipeBuilder()
            .inputs(ore('dustGarnierite') * 2)
            .inputs(ore(combustible.name) * (combustible.amount_required))
            .outputs(metaitem('ingotNickel'))
            .outputs(metaitem(combustible.byproduct) * (combustible.amount_required))
            .duration(200)
            .buildAndRegister()

    PRIMITIVEBLASTFURNACE.recipeBuilder()
            .inputs(ore('dustPentlandite') * 17)
            .inputs(ore(combustible.name) * (combustible.amount_required) * 4)
            .outputs(metaitem('ingotNickel') * 9)
            .outputs(metaitem(combustible.byproduct) * (combustible.amount_required) * 4)
            .duration(1200)
            .buildAndRegister()

    EBF.recipeBuilder()
            .inputs(ore('dustGarnierite') * 2)
            .inputs(ore(combustible.name) * (combustible.amount_required))
            .outputs(metaitem('ingotNickel'))
            .fluidOutputs(fluid('carbon_monoxide') * 1000)
            .EUt(30)
            .blastFurnaceTemp(1728)
            .duration(100)
            .buildAndRegister()
}

ELECTROLYTIC_CELL.recipeBuilder()
    .fluidInputs(fluid('nickel_sulfate_solution') * 1000)
    .fluidInputs(fluid('water') * 500)
    .notConsumable(metaitem('stickNickel'))
    .notConsumable(metaitem('graphite_electrode'))
    .fluidInputs(fluid('water') * 1500)
    .outputs(ore('dustNickel').first())
    .fluidOutputs(fluid('diluted_sulfuric_acid') * 500)
    .fluidOutputs(fluid('oxygen') * 1000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

//MOND PROCESS
EBF.recipeBuilder()
        .inputs(ore('dustPentlandite') * 17)
        .outputs(metaitem('ingotNickel') * 9)
        .fluidOutputs(fluid('sulfur_dioxide') * 8000)
        .EUt(30)
        .blastFurnaceTemp(1728)
        .duration(500)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustNickel'))
        .fluidInputs(fluid('carbon_monoxide') * 4000)
        .fluidOutputs(fluid('nickel_carbonyl') * 1000)
        .EUt(30)
        .duration(200)
        .buildAndRegister()