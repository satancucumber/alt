<template>
    <div>
        <h1> {{ plot.name }} </h1>
        <span
            v-for="str in plot.text"
            v-bind:key="str"
        >
            <span
                v-show="isDescription(str)"
            >
                <span
                    style="background-color:#ddd;"
                    @mouseleave="desLeave(str)"

                    v-show="isHold(str)"
                > {{ str }}
                </span>
                <span
                    @mouseover="desOver(str)"
                    v-show="!isHold(str)"
                > {{ str }}
                </span>
            </span>
            <span
                v-show="!isDescription(str)"
            > {{ str }}
            </span>
        </span>
        <b-row>
            <b-col>
                <div v-for="literal in plot.literals" v-bind:key="literal.id">
                    <div>
                        {{ literal.name }} -- {{ literal.description }}
                    </div>
                </div>
            </b-col>
            <b-col>
                <div v-for="formula in plot.formulas" v-bind:key="formula.id">
                    <span
                        style="background-color:#ddd;"
                        @mouseleave="desLeave(formula.description)"
                        @click="toComputer(formula.id)"
                        v-show="isHold(formula.description)"
                    > {{ formula.logform }}
                    </span>
                    <span
                        @mouseover="desOver(formula.description)"
                        v-show="!isHold(formula.description)"
                    > {{ formula.logform }}
                    </span>
                </div>
            </b-col>
            <b-col>
                <div v-for="formula in formulas" v-bind:key="formula.id">
                    <span
                        style="background-color:#ddd;"
                        @mouseleave="desLeave(formula.description)"
                        @click="toFormulas(formula.id)"
                        v-show="isHold(formula.description)"
                    > {{ formula.logform }}
                    </span>
                  <span
                      @mouseover="desOver(formula.description)"
                      v-show="!isHold(formula.description)"
                  > {{ formula.logform }}
                    </span>
                </div>
            </b-col>
        </b-row>
        <div> {{ descriptions }} </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                formulas: [],
                styleobj: {
                    'Яблоко не красное': {
                        backgroundColor:"0000"
                    }
                },
                styletext: {
                    backgroundColor:"0000"
                },
                descriptions: [],
                plot: {
                    id: 1,
                    name: 'Яблоко',
                    text: [
                        'Яблоко не красное',
                        '. ',
                        'Яблоко ароматное',
                        '. ',
                        'Если яблоко красное и яблоко ароматное, то яблоко вкусное',
                        '.'
                    ],
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
                        },
                    ],
                    formulas: [
                        {
                            id: 1,
                            description: 'Яблоко не красное',
                            operators: ['!', '*'],
                            literals: [
                                {
                                    id: 1,
                                    name: 'A',
                                    description: 'Яблоко красное',
                                    suspect: false
                                }
                            ],
                            logform: '!A',
                            desform: 'НЕ (Яблоко красное)'
                        },
                        {
                            id: 2,
                            description: 'Яблоко ароматное',
                            operators: ['*'],
                            literals: [
                                {
                                    id: 2,
                                    name: 'B',
                                    description: 'Яблоко ароматное',
                                    suspect: false
                                }
                            ],
                            logform: 'B',
                            desform: 'Яблоко ароматное'
                        },
                        {
                            id: 3,
                            description: 'Если яблоко красное и яблоко ароматное, то яблоко вкусное',
                            operators: ['=>', '&', '*', '*', '*'],
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
                            logform: '(A&B)=>C',
                            desform: 'ЕСЛИ (Яблоко красное И Яблоко ароматное), ТО Яблоко вкусное'
                        }
                    ]
                }
            }
        },
        created() {
            this.plot.formulas.forEach(f=> {
                this.descriptions.push(Object.assign({}, {name: f.description, val: false}))

            })
        },
        methods: {
            isDescription(str) {
                var formulas = this.plot.formulas;
                for (var i = 0; i < formulas.length; i++ ) {
                    if (str == formulas[i].description) {
                        return true;
                    }
                }
                return false;
            },
            val_by_name(name) {
                for (var i = 0; i < this.descriptions.length; i++) {
                    if (this.descriptions[i].name == name) {
                      return this.descriptions[i].val
                    }
                }
            },
            getIndex(list, id) {
              for (var i = 0; i < list.length; i++ ) {
                if (list[i].id == id) {
                  return i;
                }
              }
            },
            desOver(str) {
              for (var i = 0; i < this.descriptions.length; i++) {
                if (this.descriptions[i].name == str) {
                  this.descriptions[i].val = true;
                }
              }
            },
            desLeave(str) {
              for (var i = 0; i < this.descriptions.length; i++) {
                if (this.descriptions[i].name == str) {
                  this.descriptions[i].val = false;
                }
              }
            },
            isHold(str) {
                if (this.val_by_name(str)) {
                    return true;
                }
                return false;
            },
            toComputer(id) {
                var index = this.getIndex(this.plot.formulas, id);
                this.formulas.push(this.plot.formulas[index]).then(
                    this.plot.formulas.splice(index, 1)
                );
            },
            toFormulas(id) {
              var index = this.getIndex(this.formulas, id);
              this.plot.formulas.push(this.formulas[index]).then(
                  this.formulas.splice(index, 1)
              );
            },
            changecolor: function() {
                this.styletext.backgroundColor = "#bbb";
            },
            originalcolor: function() {
                this.styletext.backgroundColor = "0000";
            }
        }

    }
</script>
<style scoped>
</style>