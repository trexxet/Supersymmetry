import globals.Globals
import static globals.Petrochemistry.*

CRACKER = recipemap('cracker')
DT = recipemap('sieve_distillation')

crackables.each { _, crackable ->
    if (crackable.hydro_crackable) {
        CRACKER.recipeBuilder()
            .fluidInputs(crackable.get(1000))
            .fluidInputs(fluid('hot_hp_hydrogen') * crackable.hydrogen_required)
            .fluidOutputs(crackable.getHydro(1000))
            .fluidOutputs(fluid('fuel_gas') * crackable.gas_produced)
            .duration(80)
            .EUt(Globals.voltAmps[2])
            .buildAndRegister()  
    }
}

DT.recipeBuilder()
    .fluidInputs(fluid('hydrocracked_light_cycle_oil') * 1000)
    .fluidInputs(fluid('furfural') * 75)
    .fluidOutputs(fluid('btex_extract') * 600)
    .fluidOutputs(fluid('naphtha') * 400)
    .duration(25)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()