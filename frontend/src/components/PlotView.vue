<template>
    <div>
        Plot with prop id: {{ id }}
    </div>
</template>
<script>
    export default {
        props: {
            id: {
                type: Number,
                default: null,
        },
        },
    };
</script>
<style scoped>
</style>