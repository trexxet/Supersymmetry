import globals.Globals
import static globals.Petrochemistry.*

CRACKER = recipemap('cracker')
DT = recipemap('distillation_tower')
COKING = recipemap('coking_tower')
TUBE_FURNACE = recipemap('tube_furnace')
FLBR = recipemap('fluidized_bed_reactor')
PHASE_SEPARATOR = recipemap('phase_separator')

// Thermal Cracking
crackables.each { _, crackable -> 
    if (crackable.thermal_crackable) {
        ROASTER.recipeBuilder()
            .fluidInputs(crackable.get(1000))
            .fluidOutputs(crackable.getThermallyCracked(700))
            .duration(400)
            .EUt(Globals.voltAmps[3])
            .buildAndRegister()

        CRACKER.recipeBuilder()
            .fluidInputs(crackable.get(1000))
            .fluidOutputs(crackable.getThermallyCracked(1000))
            .duration(200)
            .EUt(Globals.voltAmps[3] * 2)
            .buildAndRegister()

        // Visbreaking
        MIXER.recipeBuilder()
            .fluidInputs(crackable.getThermallyCracked(250))
            .fluidInputs(crackable.get(600))
            .fluidInputs(fractions.heavy_gas_oil.getCrude(150))
            .fluidOutputs(crackable.getQuenched(1000))
            .duration(40)
            .EUt(30)
            .buildAndRegister()
    }
}

// Visbreaking Separation 
    DT.recipeBuilder()
        .fluidInputs(crackables.atmospheric_oil_residue.getQuenched(1000))
        .fluidOutputs(fluid('visbreaking_residue') * 570)
        .fluidOutputs(fractions.heavy_gas_oil.getCrude(150))
        .fluidOutputs(fractions.light_gas_oil.getCrude(260))
        .fluidOutputs(fractions.naphtha.getCrude(370))
        .fluidOutputs(fluid('sulfuric_fuel_gas') * 300)
        .duration(200)
        .EUt(30)
        .buildAndRegister()

    DT.recipeBuilder()
        .fluidInputs(crackables.atmospheric_oil_residue.getQuenched(1000))
        .fluidOutputs(fluid('visbreaking_residue') * 650)
        .fluidOutputs(fractions.heavy_gas_oil.getCrude(150))
        .fluidOutputs(fractions.light_gas_oil.getCrude(320))
        .fluidOutputs(fractions.naphtha.getCrude(320))
        .fluidOutputs(fluid('sulfuric_fuel_gas') * 1480)
        .duration(200)
        .EUt(30)
        .buildAndRegister()

// Coking
    // Delayed Coking
        VACUUM_DT.recipeBuilder()
            .fluidInputs(crackables.atmospheric_oil_residue.get(1000))
            .fluidInputs(fluid('coking_effluents') * 480)
            .fluidOutputs(fluid('coking_residue') * 480)
            .fluidOutputs(fractions.heavy_gas_oil.getCrude(345))
            .fluidOutputs(fractions.light_gas_oil.getCrude(200))
            .fluidOutputs(fractions.naphtha.getCrude(1510))
            .fluidOutputs(fluid('sulfuric_fuel_gas') * 1630)
            .duration(400)
            .EUt(30)
            .buildAndRegister()

        VACUUM_DT.recipeBuilder()
            .fluidInputs(fluid('slurry_oil') * 1000)
            .fluidInputs(fluid('coking_effluents') * 480)
            .fluidOutputs(fluid('coking_residue') * 480)
            .fluidOutputs(fractions.heavy_gas_oil.getCrude(345))
            .fluidOutputs(fractions.light_gas_oil.getCrude(200))
            .fluidOutputs(fractions.naphtha.getCrude(1510))
            .fluidOutputs(fluid('sulfuric_fuel_gas') * 1630)
            .duration(400)
            .EUt(30)
            .buildAndRegister()

        TUBE_FURNACE.recipeBuilder()
            .fluidInputs(fluid('coking_residue') * 1000)
            .fluidOutputs(fluid('heated_coking_residue') * 1000)
            .duration(400)
            .EUt(480)
            .buildAndRegister()

        TUBE_FURNACE.recipeBuilder() // Startup
            .fluidInputs(crackables.atmospheric_oil_residue.get(1000))
            .fluidOutputs(fluid('heated_coking_residue') * 1000)
            .duration(1000)
            .EUt(480)
            .buildAndRegister()

        COKING.recipeBuilder()
            .fluidInputs(fluid('heated_coking_residue') * 1000)
            .fluidInputs(fluid('water') * 1000)
            .outputs(metaitem('dustGreenCoke') * 32)
            .fluidOutputs(fluid('coking_effluents') * 1000)
            .duration(400)
            .EUt(30)
            .buildAndRegister()
            
    // Fluid Coking
        FLBR.recipeBuilder()
            .fluidInputs(crackables.vacuum_oil_residue.get(1000))
            .inputs(ore('dustHeatedGreenCoke') * 5)
            .fluidOutputs(fluid('coke_fines') * 1000)
            .duration(200)
            .EUt(30)
            .buildAndRegister()

        FLBR.recipeBuilder()
            .fluidInputs(fluid('slurry_oil') * 1125)
            .inputs(ore('dustHeatedGreenCoke') * 5)
            .fluidOutputs(fluid('coke_fines') * 1000)
            .duration(200)
            .EUt(30)
            .buildAndRegister()

        PHASE_SEPARATOR.recipeBuilder()
            .fluidInputs(fluid('coke_fines') * 1000)
            .outputs(metaitem('dustGreenCoke') * 15)
            .fluidOutputs(fluid('fluid_cracked_vacuum_oil_residue') * 1000)
            .duration(20)
            .buildAndRegister()

        DT.recipeBuilder()
            .fluidInputs(fluid('fluid_cracked_vacuum_oil_residue') * 1000)
            .fluidOutputs(fractions.heavy_gas_oil.getCrude(485))
            .fluidOutputs(fractions.light_gas_oil.getCrude(730))
            .fluidOutputs(fractions.naphtha.getCrude(1460))
            .fluidOutputs(fluid('sulfuric_fuel_gas') * 1480)
            .duration(200)
            .EUt(30)
            .buildAndRegister()

    // Flexicoking
        PYROLYSE_OVEN.recipeBuilder()
            .inputs(ore('dustGreenCoke') * 15)
            .fluidInputs(fluid('dense_steam') * 10000)
            .outputs(metaitem('dustHeatedGreenCoke') * 5)
            .fluidOutputs(fluid('syngas') * 7500)
            .duration(200)
            .EUt(30)
            .buildAndRegister()