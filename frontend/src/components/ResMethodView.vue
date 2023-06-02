<template>
  <div>
    <p></p>
    <h5>Собранная информация</h5>
    <p></p>
    <div style="border: solid gray">
      <div v-for="formula in formulas" v-bind:key="formula.id">
        <div>
          {{ formula.desform }}
        </div>
      </div>
    </div>
    <p></p>
    <h5>Преобразование выражений в КНФ</h5>
    <p></p>
    <div v-for="(list, i) in resFormulas" v-bind:key="i">
      <div>
        <div style="border: solid">
          {{ formulas[i].desform }}
          <span>
            <b-button type="button" class="btn btn-light" @click="changeShow(i)">
              <b-icon icon="chevron-down"></b-icon>
            </b-button>
          </span>
        </div>

        <div style="border: solid" v-show="show(i)">
        <div v-for="res in list" v-bind:key="res.id">
          <div>
            <p></p>
            <div>
              <span>
                {{ res.description }}
              </span>
            </div>
            <p></p>
            <div >
              <span style="border: solid gray">
                {{ res.desform }}
              </span>
            </div>
            <p></p>
          </div>
        </div>
          <p></p>
          <span> Разобъём на конъюнкты: </span>
          <p></p>
          <div v-for="(str, k) in resString[i]" v-bind:key="k">
            <div>
              <span> {{ k + 1 }}) {{ str }}</span>
              <p></p>
            </div>
          </div>
        </div>
        <p></p>
      </div>
    </div>



  </div>

</template>
<script>
  import {url} from "@/main";
  export default {
    props: ['mformulas'],
    name: "ResMethodView",
    data() {
      return {
        formulas: [],
        resFormulas: [],
        isShow: [],
        resString: []
      }
    },
    created() {
      this.mformulas.forEach(f => {
        this.formulas.push(f);
        this.isShow.push(Object.assign({}, {val: false}));
      })
      this.createData();

    },
    methods: {
      getData() {
        this.$http.get(url + "/resmethod").then(response => {
          const formulaslist = response && response.data ? response.data : []
          formulaslist.forEach(f => {
            this.resFormulas.push(f.map(r => {
              return {
                id: r.id,
                description: r.description,
                operators: r.operators,
                literals: r.literals,
                logform: r.logform,
                desform: r.desform
              }
            }))
          })
        })
      },
      getDataString() {
        this.$http.get(url + "/resmethod/string").then(response => {
          const stringlist = response && response.data ? response.data : []
          stringlist.forEach(f => {
            this.resString.push(f)
          })
        })
      },
      createData() {

        this.$http.post(url + "/resmethod", this.formulas).then(
            this.getData
        ).then(this.getDataString)
      },
      getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
          if (list[i].id == id) {
            return i;
          }
        }
      },
      show(i) {
        if (this.isShow[i].val) {
          return true;
        }
        return false;
      },
      changeShow(i) {
        this.isShow[i].val = !this.isShow[i].val;
      }
    }
  }
</script>
<style>

</style>