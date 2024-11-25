<template>
    <div class="background">
        <NavBar_P/>
        <ContentField>
            印刷单据
        </ContentField>
    </div>
</template>
<script>
import ContentField from "@/components/ContentField.vue";
import NavBar_P from "@/components/NavBar_P.vue";
export default{
    components: {
        ContentField,
        NavBar_P
    }
}
</script>

<style scoped>
.background {
    background-image: url("@/assets/绿色护眼.png");
    background-size: cover; /* 背景图填满整个容器 */
    background-repeat: no-repeat; /* 不重复背景图 */
    background-position: center; /* 背景图居中 */
    height: 100vh; /* 高度设置为视口高度 */
}

</style>