<template>
    <div>
        <h2>Изображение</h2>
        <b-form>
            <b-form-file
              class="formFile"
              accept="image/*"
              v-model="item.image"
              :state="Boolean(item.image)"
              placeholder="Choose a file or drop it here..."
              drop-placeholder="Drop file here..."
              @change="onChange"
            ></b-form-file>
        </b-form>
        <div id="preview">
            <b-img center id="img" wight="720px" height="540px" v-if="item.imageUrl" :src="item.imageUrl">
            </b-img>
        </div>
        <div> {{ item.imageUrl }} </div>
    </div>
</template>


<script>
export default {
  name: 'imageUpload',
  data() {
    return {
      evidences: [],
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
      item:{
          image : null,
          imageUrl: null
      }
    }
  },
  methods: {
    onChange(e) {
      const file = e.target.files[0]
      this.image = file
      this.item.imageUrl = URL.createObjectURL(file)
    },
  }
}
</script>
<style scoped>

</style>