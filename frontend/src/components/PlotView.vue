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
                    @mouseleave="descriptions[str] = false"
                    @change="desLeave($str)"
                    v-show="descriptions[str]"
                > {{ str }}
                </span>
                <span
                    @mouseover="descriptions[str] = true"
                    v-show="!descriptions[str]"
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
                        @mouseleave="descriptions[formula.description] = false"
                        v-show="descriptions[formula.description]"
                    > {{ formula.logform }}
                    </span>
                    <span
                        @mouseleave="descriptions[formula.description] = true"
                        v-show="!descriptions[formula.description]"
                    > {{ formula.logform }}
                    </span>
                </div>
            </b-col>
            <b-col>
                <div v-for="formula in formulas" v-bind:key="formula.id">
                    <div> {{ formula.logform }} </div>
                </div>
            </b-col>
        </b-row>

        <span @mouseover="upHere['upHere'] = true" @mouseleave="upHere['upHere'] = false" >
          <span> Something Something </span>
          <span v-show="upHere['upHere']"> egrgerghwer </span>
        </span>
        <div @mouseover="changecolor" @mouseleave="originalcolor" >
          <span v-bind:style="styletext"> Something Something </span>
        </div>
        <div  > {{ upHere }} </div>
        <div> {{ descriptions }} </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                upHere: {
                    'upHere': false
                },
                formulas: [],
                styleobj: {
                    'Яблоко не красное': {
                        backgroundColor:"0000"
                    }
                },
                styletext: {
                    backgroundColor:"0000"
                },
                descriptions: {},
                plot: {
                    id: 1,
                    name: 'Яблоко',
                    text: [
                        'Яблоко не красное',
                        '.  ',
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
                        }
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
            var formulas = this.plot.formulas;
            for (var i = 0; i < formulas.length; i++) {
                this.descriptions[formulas[i].description] = false;
            }
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
            desOver(str) {
                this.descriptions[str] = true;
            },
            desLeave(str) {
                this.descriptions[str] = false;
            },
            show(str) {
                return this.descriptions[str]
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