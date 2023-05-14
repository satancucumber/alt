<template>
    <div>
        <h2>Литералы</h2>
        <vue-good-table
            :columns="columns"
            :rows="literals"
            class="table"
        >
            <template slot="table-row" slot-scope="props">
                  <span v-if="props.column.field == 'before'">
                    <b-button variant="primary" @click="edit(props.row.id)">Изменить</b-button>
                    <b-button variant="primary" @click="del_row(props.row.id)">Удалить</b-button>
                  </span>
                  <span v-else-if="props.column.field == 'suspect'">
                      <b-button variant="primary" @click="change_suspect(props.row.id)">Изменить</b-button>
                      <b-form-checkbox v-model="props.row.suspect" type=boolean @click="change_suspect(props.row.id)"></b-form-checkbox>
                  </span>
                  <span v-else>
                    {{props.formattedRow[props.column.field]}}
                  </span>
            </template>
        </vue-good-table>
        <b-button variant="primary" @click="add">Добавить</b-button>
        <b-form inline id="form">
            <label class="sr-only" for="inline-form-input-name">Literals</label>
            <b-form-input
                v-model="name"
                id="inline-form-input-name"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Литерал"
            ></b-form-input>
            <b-form-input
                v-model="description"
                id="inline-form-input-name"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Описание"
            ></b-form-input>
            <b-form-checkbox
              id="checkbox-1"
              v-model="suspect"
              name="checkbox-1"
              type=boolean
            >
                Подозреваемый
            </b-form-checkbox>
            <b-button variant="primary" @click="save">Сохранить</b-button>
        </b-form>
        <b-button variant="primary">Далее</b-button>
        <div> {{ literals }} </div>
        <div> {{ text }} </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                mid: 1,
                literals: [],
                id: null,
                name: '',
                description: '',
                suspect: false,
                text: 'текст',
                columns: [
                    {
                      label: 'Before',
                      field: 'before'
                    },
                    {
                      label: 'Литерал',
                      field: 'name'
                    },
                    {
                      label: 'Описание',
                      field: 'description'
                    },
                    {
                      label: 'Подозреваемый',
                      field: 'suspect',

                    }
                  ]
            }
        },
        methods: {
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
                this.name = '',
                this.description = '',
                this.suspect = false
            },
            add() {
                document.getElementById("form").style.display = "block";
            },
            setId(literal, id) {
                literal.id = id;
            },
            save() {
                var literal = {
                    id: this.id,
                    name: this.name,
                    description: this.description,
                    suspect: this.suspect
                };
                if (this.id) {
                    var index = this.getIndex(this.literals, this.id);
                    this.literals.splice(index, 1, literal);
                    this.put_text('Изменено');
                } else {
                    this.setId(literal, this.mid++);
                    this.literals.push(literal);
                    this.put_text(this.id);
                }
                this.clean();
            },
            put_text(text) {
                this.text = text;
            },
            edit(id) {
                document.getElementById("form").style.display = "block";
                var index = this.getIndex(this.literals, id);
                this.name = this.literals[index].name;
                this.id = this.literals[index].id;
                this.description = this.literals[index].description;
                this.suspect = this.literals[index].suspect;
                this.put_text('Изменить');
            },
            del_row(id) {
                var index = this.getIndex(this.literals, id);
                this.literals.splice(this.literals.indexOf(this.literals[index]), 1);
                this.put_text('Удалить');
            },
            change_suspect(id) {
                var index = this.getIndex(this.literals, id);
                this.literals[index].suspect = !this.literals[index].suspect;
            }
        }
    };
</script>
<style scoped>
    #form {
        display: none
    }
</style>