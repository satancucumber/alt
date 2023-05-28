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
                                        :readonly="isRead(formula.id)"
                                        id="textarea-auto-height"
                                        placeholder="Описание формулы в тексте"
                                        rows="2"
                                        max-rows="8"
                                        v-model="descriptions[formula.id]"
                                    ></b-form-textarea>
                                  <p v-show="!isRead(formula.id)" > {{ alerttext }} </p>
                                </b-form>
                            </b-col>
                            <b-col>
                                  <b-button v-show="!isRead(formula.id)" @click="save_description(formula.id)"> Сохранить </b-button>
                                  <b-button :disabled="!isOpen" v-show="isRead(formula.id)" @click="edit_description(formula.id)"> Изменить </b-button>
                            </b-col>
                        </b-row>
                    </b-form>
                </b-col>
            </b-row>
        </div>
        <b-button @click="toArr"> Сохранить </b-button>
        <div> descriptions: {{ descriptions }} </div>
        <div> read: {{ read }} </div>
        <div> {{ textarr }} </div>
        <div> {{ indexes }} </div>
        <div> alert: {{ alerttext }} </div>
        <div> formulas: {{ formulas }} </div>

    </div>
</template>
<script>
    export default {
      data() {
        return {
            indexes: [],
            alerttext : '',
            text: 'Яблоко не красное. Яблоко ароматное. Если яблоко красное и яблоко ароматное, то яблоко вкусное.',
            textarr: [],
            descriptions: {},
            read: [],
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
        this.formulas.forEach(f=> {
          this.read.push(Object.assign({}, {id: f.id, val: true}))
        });
        this.formulas.forEach(f=> {
          this.descriptions[f.id] = '';
        });
        this.formulas.forEach(f=> {
          this.indexes.push(Object.assign({}, {id: f.id, val: [0, 0]}))
        });
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
        getIndex(list, id) {
            for (var i = 0; i < list.length; i++ ) {
                if (list[i].id == id) {
                    return i;
                }
            }
        },
        inText(str) {
            if (this.text.indexOf(str) != -1) {
              return true;
            }
            return false;
        },
        save_description(id) {
            if (this.inText(this.descriptions[id])) {
              var index1 = this.getIndex(this.read, id);
              var index2 = this.getIndex(this.formulas, id);
              var index3 = this.getIndex(this.indexes, id);
              this.read[index1].val = true;
              this.formulas[index2].description = this.descriptions[id];
              this.indexes[index3].val[0] = this.text.toString().indexOf(this.descriptions[id]);
              this.indexes[index3].val[1] = this.text.toString().indexOf(this.descriptions[id]) + this.descriptions[id].length;

            }
            else {
              this.alerttext = 'Описание не содержится в тексте!';
            }
        },
        isOpen() {
          for (var i = 0; i < this.read.length; i++) {
            if (!this.read[i].val) {
              return true;
            }
          }
          return false;
        },
        edit_description(id) {
            if (!this.isOpen()) {
              var index = this.getIndex(this.read, id);
              this.read[index].val = false;
              this.alerttext = '';
            }

        },
        isRead(id) {
            var index = this.getIndex(this.read, id);
            if (this.read[index].val) {
              return true
            }
            return false
        },
        toArr() {
          var indexes = this.indexes.sort((a, b) => a.val - b.val);
          var start = 0;
          var end = this.text.length;
          for (var i = 0; i < indexes.length; i++) {
            end = indexes[i].val[0];
            if (start != end) {
              this.textarr.push(this.text.slice(start, end));
              start = end;
            }
            end = indexes[i].val[1];
            this.textarr.push(this.text.slice(start, end));
            start = end;
          }
          if (end != this.text.length) {
            end = this.text.length;
            this.textarr.push(this.text.slice(start, end));
          }
        },
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