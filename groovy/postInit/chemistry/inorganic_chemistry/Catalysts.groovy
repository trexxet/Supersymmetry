import globals.Globals


MIXER = recipemap('mixer')
SINTERING_OVEN = recipemap('sintering_oven')

// Alumina supports
MIXER.recipeBuilder()
    .inputs(ore('dustAmmoniumHexachloroplatinate') * 17)
    .fluidInputs(fluid('phosphoric_acid') * 1000)
    .fluidOutputs(fluid('platinum_precursor_solution') * 1000)
    .duration(100)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

SINTERING_OVEN.recipeBuilder()
    .inputs(ore('dustAlumina') * 5)
    .fluidInputs(fluid('platinum_precursor_solution') * 1000)
    .outputs(metaitem('dustSupportedPlatinum'))
    .fluidOutputs(fluid('phosphoric_acid') * 1000)
    .duration(100)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

MIXER.recipeBuilder()
    .inputs(ore('dustPalladiumChloride') * 3)
    .fluidInputs(fluid('phosphoric_acid') * 1000)
    .fluidOutputs(fluid('palladium_precursor_solution') * 1000)
    .duration(100)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

SINTERING_OVEN.recipeBuilder()
    .inputs(ore('dustAlumina') * 5)
    .fluidInputs(fluid('palladium_precursor_solution') * 1000)
    .outputs(metaitem('dustSupportedPalladium'))
    .fluidOutputs(fluid('phosphoric_acid') * 1000)
    .duration(100)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()