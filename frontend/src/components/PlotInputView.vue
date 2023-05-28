<template>
    <div>
        <literal-form id="literalform" :literals="literals"></literal-form>
        <b-button variant="primary" @click="shownext">Далее</b-button>
        <div> literals {{ literals }} </div>
        <formula-form :mliterals="literals" :mformulas="formulas"></formula-form>\
        <div> formulas {{ formulas }} </div>
    </div>
</template>
<script>
    import LiteralForm from "./LiteralFormView.vue"
    import FormulaForm from "./FormulaFormView.vue"
    export default {
      name: 'PlotInputView',
      components: {
        LiteralForm,
        FormulaForm
      },
      data() {
        return {
            id: null,
            name: '',
            literals: [],
            formulas: [],
            plot: {}
        }
      },
      methods: {
        shownext() {
            document.getElementById("formliteral").style.display = "none";
        }
      }
    }
</script>
<style scoped>
    #literalform {
        display: block
    }
</style>