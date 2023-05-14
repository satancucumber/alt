<template>
    <div>
        <h2>Canvas</h2>
        <p>{{x}}, {{y}}</p>
        <p>{{ mouse }}</p>

        <div>
            <canvas id="mycanvas" wight="1200px" height="540px"
                @mousemove="showCoordinates"
                @mousedown="down"
                @mouseup="up"
            />
        </div>

        <div>{{ triangle }}</div>
    </div>
</template>


<script>
export default {
  name: 'CanvasView',
  data() {
    return {
      mouse: false,
      canvas: null,
      x: null,
      y: null,
      triangle: {
        x1: null,
        y1: null,
        x2: null,
        y2: null,
      }
    }
  },
  methods: {
    up(e) {
        this.mouse = false;
        this.showCoordinates(e);
        this.showTriangle(this.x, this.y)
    },
    showTriangle(x, y) {
        let ctx = this.canvas;
        ctx.beginPath();
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 1;
        ctx.moveTo(this.triangle.x1, this.triangle.y1);
        ctx.lineTo(this.triangle.x1, y);
        ctx.lineTo(x, this.triangle.y1);
        ctx.moveTo(x, y);
        ctx.lineTo(this.triangle.x1, y);
        ctx.lineTo(x, this.triangle.y1);
        ctx.stroke();
        ctx.closePath();
    },
    down(e) {
        this.mouse = true;
        this.showCoordinates(e);
        if (!this.triangle.x1) {
            if (!this.triangle.y1) {
                this.triangle.x1 = this.x
                this.triangle.y1 = this.y
            } else {
                this.triangle.y1 = this.y
            }
        } else {
            this.showTriangle(this.x, this.y)
        }
    },
    showCoordinates(e) {
      this.x = e.offsetX;
      this.y = e.offsetY;
    }
  }
}
</script>
<style scoped>
    #mycanvas {
      border: 1px solid grey;
    }
</style>