<template>
    <div class="background">
        <NavBar_A/>
        <ContentField>
            采购清单
        </ContentField>
    </div>
</template>
<script>
import ContentField from "@/components/ContentField.vue";
import NavBar_A from "@/components/NavBar_A.vue";
export default{
    components: {
        ContentField,
        NavBar_A
    }
}
</script>

<style scoped>
.background {
    background-image: url("@/assets/轻音少女.png");
    background-size: cover; /* 背景图填满整个容器 */
    background-repeat: no-repeat; /* 不重复背景图 */
    background-position: center; /* 背景图居中 */
    height: 100vh; /* 高度设置为视口高度 */
}
</style>