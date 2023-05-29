<template>
    <div>
        <main-menu></main-menu>
        <b-form-input
            v-show="isName"
            v-model="name"
            id="inline-form-input-name"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="Название сюжета"
        ></b-form-input>
        <b-button v-show="isName" variant="primary" @click="isName = false; isLiterals = true">Далее</b-button>
        <literal-form v-show="isLiterals" id="literalform" :literals="literals"></literal-form>
        <b-button v-show="isLiterals" variant="primary" @click="isLiterals = false; isFormulas = true">Далее</b-button>
        <formula-form v-show="isFormulas" :mliterals="literals" :mformulas="formulas"></formula-form>
        <b-button v-show="isFormulas" variant="primary" @click="isFormulas = false; isText = true; textKey += 1;">Далее</b-button>

        <text-form v-show="isText" :mformulas="formulas" :mtext="text" :key="textKey"></text-form>
        <b-button v-show="isText" variant="primary" @click="savePlot">Сохранить и перейти к решению</b-button>

        <Plot v-show="isPlot" :mplot="plot" :key="plotKey"></Plot>
        <b-button v-show="isPlot" variant="primary" @click="postPlot">Опубликовать</b-button>
    </div>
</template>
<script>
    import MainMenu from "./MainView.vue"
    import LiteralForm from "./LiteralFormView.vue"
    import FormulaForm from "./FormulaFormView.vue"
    import TextForm from "./TextFormView.vue"
    import Plot from "@/components/PlotView.vue";
    import {url} from "@/main";
    export default {
      name: 'PlotInputView',
      components: {
        Plot,
        MainMenu,
        LiteralForm,
        FormulaForm,
        TextForm
      },
      data() {
        return {
            textKey: 0,
            plotKey: 0,
            isPlot: false,
            isName: true,
            isLiterals: false,
            isFormulas: false,
            isText: false,
            id: null,
            name: '',
            literals: [],
            formulas: [],
            text: [],
            plot: null
        }
      },
      methods: {
            savePlot() {
              var plot = {
                name: this.name,
                text: this.text,
                literals: this.literals,
                formulas: this.formulas
              }
              this.plot = plot;
              this.isText = false;
              this.isPlot = true;
              this.plotKey += 1;
            },
            postPlot() {
              var plot = {
                name: this.name,
                text: this.text,
                literals: this.literals,
                formulas: this.formulas
              }
              this.$http.post(url + "/plot", plot)
            }
      },

    }
</script>
<style scoped>
    #literalform {
        display: block
    }
</style>