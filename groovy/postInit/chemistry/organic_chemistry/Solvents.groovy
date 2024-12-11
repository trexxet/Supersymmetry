import globals.Globals

CSTR = recipemap('continuous_stirred_tank_reactor')
BCR = recipemap('bubble_column_reactor')

// TODO: move other organic solvents (THF, TCE etc.) here

// DMC (dimethyl carbonate)

CSTR.recipeBuilder()
        .fluidInputs(fluid('phosgene') * 50)
        .fluidInputs(fluid('methanol') * 100)
        .fluidOutputs(fluid('dimethyl_carbonate') * 50)
        .fluidOutputs(fluid('hydrogen_chloride') * 100)
        .duration(1)
        .EUt(30)
        .buildAndRegister()

// EC (ethylene carbonate)

BCR.recipeBuilder()
        .fluidInputs(fluid('ethylene_oxide') * 50)
        .fluidInputs(fluid('carbon_dioxide') * 50)
        .fluidOutputs(fluid('ethylene_carbonate') * 50)
        .duration(5)
        .EUt(30)
        .buildAndRegister()

/*
 * Organochlorines
 */

// 1,2-DCE (1,2-dichloroethane)
// direct chlorination
FBR.recipeBuilder()
    .fluidInputs(fluid('chlorine') * 100)
    .fluidInputs(fluid('ethylene') * 50)
    .notConsumable(ore('catalystBedIronIiiOxide'))
    .fluidOutputs(fluid('dichloroethane') * 50)
    .EUt(30)
    .duration(10)
    .buildAndRegister()
// oxychlorination
FBR.recipeBuilder()
    .fluidInputs(fluid('ethylene') * 50)
    .fluidInputs(fluid('hydrochloric_acid') * 100)
    .fluidInputs(fluid('oxygen') * 50)
    .notConsumable(ore('catalystBedCopperIiChloride'))
    .fluidOutputs(fluid('dichloroethane') * 50)
    .fluidOutputs(fluid('water') * 150)
    .EUt(30)
    .duration(10)
    .buildAndRegister()
