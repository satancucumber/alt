<template>
    <div>
        <h2>Текст</h2>
        <b-form id="textform">
            <b-form-textarea
                id="textarea-auto-height"
                placeholder="Текст сюжета"
                v-model="text"
                rows="10"
                max-rows="20"
            ></b-form-textarea>
            <b-button @click="save_text"> Сохранить </b-button>
        </b-form>
        <b-form id="textfield">
            <b-form-textarea readonly
                id="textarea-auto-height"
                placeholder="Текст сюжета"
                v-model="text"
                rows="10"
                max-rows="20"
            ></b-form-textarea>
            <b-button @click="edit_text"> Изменить </b-button>
        </b-form>
        <div v-for="formula in formulas" v-bind:key="formula.id">
            <b-row>
                <b-col>
                    {{ formula.formula }}
                </b-col>
                <b-col>
                    <b-form>
                        <b-row>
                            <b-col>
                                <b-form>
                                    <b-form-textarea
                                        id="textarea-auto-height"
                                        placeholder="Описание формулы в тексте"
                                        rows="2"
                                        max-rows="8"
                                        v-model="descriptions[formula.id]"
                                    ></b-form-textarea>
                                </b-form>
                            </b-col>
                            <b-col>
                                <b-button-group>
                                    <b-button @click="save_description(formula.id)"> Сохранить </b-button>
                                    <b-button @click="edit_description(formula.id)"> Изменить </b-button>
                                </b-button-group>
                            </b-col>
                        </b-row>
                    </b-form>
                </b-col>
            </b-row>
        </div>
        <div> descriptions: {{ descriptions }} </div>
        <div> alert: {{ alerttext }} </div>
    </div>
</template>
<script>
    export default {
      data() {
        return {
            alerttext : '',
            text: '',
            descriptions: {},
            formulas: [
                {
                    id: 1,
                    description: '',
                    operators: ['!', '*'],
                    literals: [
                        {
                            id: 1,
                            name: 'A',
                            description: 'Яблоко красное',
                            suspect: false
                        }
                    ],
                    formula: '!A'
                },
                {
                    id: 2,
                    description: '',
                    operators: ['*'],
                    literals: [
                        {
                            id: 2,
                            name: 'B',
                            description: 'Яблоко ароматное',
                            suspect: false
                        }
                    ],
                    formula: 'B'
                },
                {
                    id: 3,
                    description: '',
                    operators: ['(', '*', '&', '*', ')', '=>', '*'],
                    literals: [
                        {
                            id: 1,
                            name: 'A',
                            description: 'Яблоко красное',
                            suspect: false
                        },
                        {
                            id: 2,
                            name: 'B',
                            description: 'Яблоко ароматное',
                            suspect: false
                        },
                        {
                            id: 3,
                            name: 'C',
                            description: 'Яблоко вкусное',
                            suspect: true
                        }
                    ],
                    formula: '(A&B)=>C'
                },
            ],
        }
      },
      created() {
        for (var i = 0; i < this.formulas.length; i++) {
            this.descriptions[this.formulas[i].id] = '';
        }
      },
      methods: {
        save_text() {
            document.getElementById("textform").style.display = "none";
            document.getElementById("textfield").style.display = "block";
        },
        edit_text() {
            document.getElementById("textfield").style.display = "none";
            document.getElementById("textform").style.display = "block";
        },
        save_description(id) {
            this.alerttext = this.descriptions[id];
        },
        edit_description(id) {
            this.alerttext = this.descriptions[id];
        }
      },
    }
</script>
<style scoped>
    #textfield {
        display: none;
    };
    #textform {
        display: block;
    };
</style>