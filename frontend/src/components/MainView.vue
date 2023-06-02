<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand>
        <b-icon icon="justify" v-b-toggle.sidebar-1></b-icon>
      </b-navbar-brand>
      <b-navbar-brand href="/">
        Логический детектив
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Правила</b-nav-item>
          <b-nav-item href="\input">Добавить</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-sidebar id="sidebar-1" title="Сюжеты" shadow>
      <div class="px-3 py-2" v-for="plot in plots" v-bind:key="plot.id">
        <button type="button" class="btn btn-link" @click="showPlot(plot.id)"> {{ plot.name }} </button>
      </div>
    </b-sidebar>

    <Plot v-show="isPlot" :mplot="plot" :key="plotKey"></Plot>

  </div>
</template>

<script>
import {url} from "@/main";
import Plot from "./PlotView.vue"
export default {
  components: {
    Plot
  },
  name: 'MainView',
  data() {
    return{
      plotKey: 0,
      isPlot: false,
      plot: null,
      id: null,
      plots: []
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      this.$http.get(url + "/plot").then(response => {
        const plots = response && response.data ? response.data : []
        this.plots = plots.map(r => {
          return {
            id: r.id,
            name: r.name,
            text: r.text,
            formulas: r.formulas
          }
        })
      })
    },
    getIndex(list, id) {
      for (var i = 0; i < list.length; i++ ) {
        if (list[i].id == id) {
          return i;
        }
      }
    },
    showPlot(id) {
      var index = this.getIndex(this.plots, id);
      this.plot = this.plots[index];
      this.isPlot = true;
      this.plotKey += 1;
    },
  }
}
</script>
<style>

</style>