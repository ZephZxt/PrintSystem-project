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
import $ from "jquery";
import { useStore } from "vuex";
export default{
    components: {
        ContentField,
        NavBar_P
    },
    setup() {
        const store = useStore();
        // $.ajax({
        //     url: "http://127.0.0.1:4000/docs/add/",
        //     type: "POST",
        //     data: {
        //         pName: "武汉理工大学出版社",
        //         bName: "数据库系统原理",
        //         font: "宋体",
        //         mName: "A4纸",
        //         num: 10,
        //     },
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     success(resp) {
        //         console.log(resp);
        //     },
        //     error(resp) {
        //         console.log(resp);
        //     }
        // })
        $.ajax({
            url: "http://127.0.0.1:4000/docs/get/",
            type: "GET",
            
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
                console.log(resp);
            },
            error(resp) {
                console.log(resp);
            }
        })

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