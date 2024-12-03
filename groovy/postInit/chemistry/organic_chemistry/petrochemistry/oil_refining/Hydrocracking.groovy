import globals.Globals
import static globals.Petrochemistry.*

CRACKER = recipemap('cracker')
DT = recipemap('sieve_distillation')

crackables.each { _, crackable ->
    if (crackable.hydro_crackable) {
        CRACKER.recipeBuilder()
            .fluidInputs(crackable.get(4000))
            .fluidInputs(fluid('hot_hp_hydrogen') * (4 * crackable.hydrogen_consumed))
            .fluidOutputs(crackable.getHydro(4000))
            .fluidOutputs(fluid('fuel_gas') * (4 * crackable.gas_produced))
            .circuitMeta(2)
            .duration(200)
            .EUt(Globals.voltAmps[2])
            .buildAndRegister()  
    }
}

DT.recipeBuilder()
    .fluidInputs(fluid('hydrocracked_light_gas_oil') * 1000)
    .fluidOutputs(fluid('light_gas_oil') * 535)
    .fluidOutputs(fluid('naphtha') * 1780)
    .duration(50)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('hydrocracked_light_cycle_oil') * 1000)
    .fluidInputs(fluid('furfural') * 40)
    .fluidOutputs(fluid('naphtha') * 450)
    .fluidOutputs(fluid('btex_extract') * 300)
    .duration(50)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('hydrocracked_heavy_gas_oil') * 1000)
    .fluidOutputs(fluid('heavy_gas_oil') * 200)
    .fluidOutputs(fluid('light_gas_oil') * 500)
    .fluidOutputs(fluid('naphtha') * 710)
    .duration(50)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('hydrocracked_atmospheric_oil_residue') * 1000)
    .fluidOutputs(fluid('atmospheric_oil_residue') * 300)
    .fluidOutputs(fluid('heavy_gas_oil') * 660)
    .fluidOutputs(fluid('light_gas_oil') * 920)
    .fluidOutputs(fluid('naphtha') * 490)
    .duration(50)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('hydrocracked_vacuum_oil_residue') * 1000)
    .fluidOutputs(fluid('vacuum_oil_residue') * 270)
    .fluidOutputs(fluid('heavy_gas_oil') * 690)
    .fluidOutputs(fluid('light_gas_oil') * 900)
    .fluidOutputs(fluid('naphtha') * 455)
    .duration(50)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()
