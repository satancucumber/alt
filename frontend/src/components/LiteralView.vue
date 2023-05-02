<template>
    <div>
        <h2>Литералы</h2>
        <vue-good-table
            :columns="columns"
            :rows="literals"
            class="table"
            id="table"
        >
            <template slot="table-row" slot-scope="props">
                  <span v-if="props.column.field == 'before'">
                    <b-button variant="primary" @click="edit(props.row.id)">Изменить</b-button>
                    <b-button variant="primary" @click="delete(props.row.id)">Удалить</b-button>
                  </span>
                  <span v-else>
                    {{props.formattedRow[props.column.field]}}
                  </span>
            </template>
        </vue-good-table>
        <b-button variant="primary" @click="addliteral">Добавить</b-button>
        <b-form id="form">
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
            <b-form-input
                v-model="suspect"
                id="inline-form-input-name"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Преступник"
                type="Boolean"
            ></b-form-input>
            <b-button variant="primary" @click="save">Сохранить</b-button>
        </b-form>
        <b-button variant="primary">Далее</b-button>
    </div>
</template>
<script>
    import {url} from "@/main";
    export default {
        data() {
            return {
                literals: [],
                id: null,
                name: '',
                description: '',
                suspect: false,
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
                      type: Boolean
                    }
                  ]
            }
        },
        methods: {
            getData() {
                this.$http.get(url + "/literal").then(response => {
                    const literals = response && response.data ? response.data : []
                    this.literals = literals.map(r => {
                        return {
                            id: r.id,
                            name: r.name,
                            description: r.description,
                            suspect: r.suspect
                        }
                    })
                })
              },
              getIndex(list, id) {
                  for (var i = 0; i < list.length; i++ ) {
                      if (list[i].id === id) {
                          return i;
                      }
                  }
              },
            clean() {
                this.id = null;
                this.name = '',
                this.description = '',
                this.suspect = false
            },
            add() {
                document.getElementById("form").style.display = "block";
                document.getElementById("table").style.display = "block";
            },
            save() {
                var literal = {
                    name: this.name,
                    description: this.description
                };
                if (this.id) {
                    this.$http.put(url + "/literal/" + this.id.toString(), literal).
                        then(()=>this.clean()).
                        then(()=>this.getData())

                } else {
                    this.$http.post(url + "/literal", literal).
                        then(()=>this.clean()).
                        then(()=>this.getData())
                }
            },
            editRow(id) {
                var index = this.getIndex(this.literals, id);
                document.getElementById("form").style.display = "block";
                this.name = this.literals[index].name;
                this.id = this.literals[index].id;
                this.description = this.literals[index].description;
                this.suspect = this.literals[index].suspect;
            },
            deleteRow(id) {
                this.$http.delete(url + "/literal/" + id.toString()).
                        then(()=>this.getData())
            }
        }
    };
</script>
<style scoped>
    #table {
        display: none
    };
    #form {
        display: none
    }
</style>