<template>
  <b-img center :src="img" style="position: relative;">
      <b-button id=evidence></b-button>
  </b-img>
</template>

<script>
export default {
  data() {
    return {
      img: 'blob:http://localhost:8080/abe00656-452d-4aab-8555-920009bd2dcb',
      styleEvidence: {
        border: "solid",
        weight: "100px",
        high: "100px",
        top: "50px",
        bottom: "50px",
        left: "500px",
        right: "50px"
      },
      top: null,
      bottom: null,
      left: null,
      right: null
    }
  }
}
</script>
<style>
  #evidence {
    position: absolute;
    border: solid;
    high: 500px;
    top: 50px;
    right: 500px;
    left: 500px;
  }
</style>