import globals.Globals
import static globals.Petrochemistry.*

BCR = recipemap("bubble_column_reactor")
CRACKER = recipemap("cracker")
ROASTER = recipemap("roaster")
DT = recipemap("sieve_distillation")

/* Data:
- Slurry oil: 40 carbons
- Catalytic overheads: 1.825 carbons
*/

// Standard FCC

crackables.each { _, crackable -> 
    if (crackable.catalytic_crackable) {
        CRACKER.recipeBuilder()
            .fluidInputs(crackable.get(1000))
            .inputs(metaitem('cracking_catalyst'))
            .fluidOutputs(crackable.getCrudeCatalyticallyCracked(1000))
            .duration(200)
            .EUt(Globals.voltAmps[1] * 2)
            .buildAndRegister()

        DT.recipeBuilder()
            .fluidInputs(crackable.getCrudeCatalyticallyCracked(500))
            .fluidInputs(fluid('dense_steam') * 50)
            .fluidOutputs(crackable.getCatalyticallyCracked(500))
            .outputs(metaitem('spent_cracking_catalyst'))
            .duration(100)
            .EUt(Globals.voltAmps[1])
            .buildAndRegister()
    }
}

ROASTER.recipeBuilder()
    .fluidInputs(fluid('oxygen') * 1000)
    .inputs(metaitem('spent_cracking_catalyst') * 4)
    .fluidOutputs(fluid('flue_gas') * 1000)
    .outputs(metaitem('cracking_catalyst') * 4)
    .duration(100)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('catalytically_cracked_heavy_gas_oil') * 1000)
    .fluidOutputs(fractions.heavy_cycle_oil.get(40))
    .fluidOutputs(fractions.light_cycle_oil.get(60))
    .fluidOutputs(fractions.naphtha.get(500))
    .fluidOutputs(fluid('catalytic_overheads') * 400)
    .duration(200)
    .EUt(Globals.voltAmps[1] * 2)
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('catalytically_cracked_atmospheric_oil_residue') * 1000)
    .fluidOutputs(fluid('slurry_oil') * 380)
    .fluidOutputs(fractions.heavy_cycle_oil.getSulfuric(300))
    .fluidOutputs(fractions.light_cycle_oil.getSulfuric(450))
    .fluidOutputs(fractions.naphtha.getCrude(1510))
    .fluidOutputs(fluid('sulfuric_catalytic_overheads') * 1130)
    .duration(200)
    .EUt(Globals.voltAmps[1] * 2)
    .buildAndRegister()

DT.recipeBuilder()
    .fluidInputs(fluid('catalytically_cracked_vacuum_oil_residue') * 1000)
    .fluidOutputs(fluid('slurry_oil') * 430)
    .fluidOutputs(fractions.heavy_cycle_oil.getSulfuric(280))
    .fluidOutputs(fractions.light_cycle_oil.getSulfuric(430))
    .fluidOutputs(fractions.naphtha.getCrude(850))
    .fluidOutputs(fluid('sulfuric_catalytic_overheads') * 850)
    .duration(200)
    .EUt(Globals.voltAmps[1] * 2)
    .buildAndRegister()