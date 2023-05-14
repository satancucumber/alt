<template>
    <div>
        <h2>Формулы</h2>
        <vue-good-table
            :columns="columns"
            :rows="formulas"
            class="table"
        >
            <template slot="table-row" slot-scope="props">
                  <span v-if="props.column.field == 'before'">
                    <b-button variant="primary" @click="edit(props.row.id)">Изменить</b-button>
                    <b-button variant="primary" @click="del_row(props.row.id)">Удалить</b-button>
                  </span>
                  <span v-else>
                    {{props.formattedRow[props.column.field]}}
                  </span>
            </template>
        </vue-good-table>

        <b-button variant="primary" @click="add">Добавить</b-button>
        <div id="form">
            <b-row>
                <b-col sm="3">
                    <b-form-input readonly
                        v-model="formula"
                    ></b-form-input>
                </b-col>
                <b-col>
                    <b-button @click="del_symbol"> X </b-button>
                </b-col>
            </b-row>
            <b-row>
                <b-col sm="1">
                    <b-button-group v-for="operator in const_operators" v-bind:key="operator">
                        <b-button @click="add_operator(operator)"> {{ operator }} </b-button>
                    </b-button-group>
                </b-col>
                <b-col sm="1">
                    <b-button-group v-for="literal in const_literals" v-bind:key="literal.id">
                        <b-button @click="add_literal(literal)"> {{ literal.name }} </b-button>
                    </b-button-group>
                </b-col>
            </b-row>
            <b-button variant="primary" @click="save"> Сохранить </b-button>
        </div>


        <div>operators: {{ operators }}</div>
        <div>literals: {{ literals }}</div>
        <div v-for="formula in formulas" v-bind:key="formula.id"> <div> {{ formula }} </div> </div>
        <div> {{ formula }}</div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                mid: 1,
                id: null,
                literals: [],
                operators: [],
                formulas: [],
                formula: '',
                last_symbol: '',
                const_literals: [
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
                    },
                ],
                const_operators: [
                    '(', ')',
                    '!',
                    '&', '|',
                    '=>', '<=>'
                ],
                columns: [
                    {
                      label: 'Before',
                      field: 'before'
                    },
                    {
                      label: 'Формула',
                      field: 'formula'
                    }
                ],
            }
        },
        methods: {
            add_operator(operator) {
                this.formula = this.formula + operator;
                this.operators.push(operator);
                this.last_symbol = operator;
            },
            add_literal(literal) {
                this.formula = this.formula + literal.name;
                this.literals.push(literal);
                this.operators.push('*');
                this.last_symbol = literal.name;
            },
            update_last_symbol() {
                if(this.formula != '') {
                    if(this.operators[this.operators.length - 1] == '*') {
                        this.last_symbol = this.literals[this.literals.length - 1].name;
                    } else {
                        this.last_symbol = this.operators[this.operators.length - 1];
                    }
                } else {
                    this.last_symbol = '';
                }
            },
            del_symbol() {
                if(this.last_symbol != '') {
                    var index = this.formula.lastIndexOf(this.last_symbol);
                    if(this.last_symbol == this.operators[this.operators.length - 1]) {
                        this.operators.pop();
                    } else {
                        this.formula = this.formula.substring(0, index);
                        this.literals.pop();
                        this.operators.pop();
                    }
                    this.formula = this.formula.substring(0, index);
                    this.update_last_symbol()
                }

            },
            add() {
                document.getElementById("form").style.display = "block";
            },
            getIndex(list, id) {
                for (var i = 0; i < list.length; i++ ) {
                    if (list[i].id == id) {
                        return i;
                    }
                }
            },
            clean() {
                document.getElementById("form").style.display = "none";
                this.id = null;
                this.formula = '';
                this.operators = [];
                this.literals = [];
            },
            save() {
                var formula = {
                    id: this.id,
                    description: '',
                    operators: this.operators,
                    literals: this.literals,
                    formula: this.formula
                };
                if (this.id) {
                    var index = this.getIndex(this.formulas, this.id);
                    this.formulas.splice(index, 1, formula);
                } else {
                    formula.id = this.mid++;
                    this.formulas.push(formula);
                }
                this.clean()
            },
            edit(id) {
                var index = this.getIndex(this.formulas, id);
                document.getElementById("form").style.display = "block";
                this.id = this.formulas[index].id;
                this.formula = this.formulas[index].formula;
                this.operators = this.formulas[index].operators;
                this.literals = this.formulas[index].literals;
            },
            del_row(id) {
                var index = this.getIndex(this.formulas, id);
                this.formulas.splice(index, 1);
            }
        }
    };
</script>
<style scoped>
    #form {
        display: none
    }
</style>