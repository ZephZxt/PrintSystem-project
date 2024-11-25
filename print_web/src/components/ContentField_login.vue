<template>
    <div class="container-fluid content-field">
        <div class="card">
            <div class="card-body">
                <slot></slot>
            </div>
        </div>
    </div>
</template>
<script>
</script>
<style scoped>
div.content-field {
    position: fixed; /* 如果要使用全页的 card，可以保持这个 */
    top: 0;
    
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url(@/assets/2.jpg);
    background-size: cover;
}

div.card {

    width: 90%;
    max-width: 800px; 
    padding: 20px;;       /* 宽度为 100% */
    height: 50%;       /* 高度为 100% */ 
}
</style>