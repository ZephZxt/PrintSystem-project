<template>
    <div class="background">
        <NavBar_P/>
        <div class="card">
            <div class="card-header">
                <button type="button" class="btn btn-light" style="font-size: 120%;" @click="refresh_tasks(3)">全部印刷任务</button>
                <button type="button" class="btn btn-success my-custom-button" @click="refresh_tasks(1)">已完成</button>
                <button type="button" class="btn btn-secondary my-custom-button" @click="refresh_tasks(0)">未开始</button>
                <button type="button" class="btn btn-primary my-custom-button" @click="refresh_tasks(2)">进行中</button>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>印刷任务编号</th>
                                <th>印刷单据编号</th>
                                <th>出版商编号</th>
                                <th>出版商名称</th>
                                <th>书籍名称</th>
                                <th>印刷字体</th>
                                <th>印刷数量</th>
                                <th>印刷部门编号</th>
                                <th>印刷部门名称</th>
                                <th>印刷部门电话</th>
                                <th>预计完成时间</th>
                                <th>印刷任务状态</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="task in tasks" :key="task.tno">
                                <td>{{ task.tno }}</td>
                                <td>{{ task.dno }}</td>
                                <td>{{ task.pno }}</td>
                                <td>{{ task.pname }}</td>
                                <td>{{ task.bname }}</td>
                                <td>{{ task.font }}</td>
                                <td>{{ task.num }}</td>
                                <td>{{ task.pdNo }}</td>
                                <td>{{ task.pdName }}</td>
                                <td>{{ task.teleNumber }}</td>
                                <td>{{ task.time }}</td>
                                <td>
                                    <span v-if="task.state == 0" style="color: red;">未开始</span>
                                    <span v-if="task.state == 2" style="color: blue;">进行中</span>
                                    <span v-if="task.state == 1" style="color: green;">已完成</span>
                                </td>
                                <td>
                                    
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import NavBar_P from "@/components/NavBar_P.vue";
import { useStore } from "vuex";
import $ from "jquery";
import { ref } from "vue";
export default{
    components: {
        NavBar_P
    },
    setup() {
        const store = useStore();
        let tasks = ref([]);

        const refresh_tasks = (filter) => {
            $.ajax({
                url: "http://127.0.0.1:4000/tasks/get/",
                type: "GET",
                data: {
                    filter: filter
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    tasks.value = resp;
                    console.log(resp);
                }
            })
        }
        refresh_tasks(3);

        return {
            tasks,
            refresh_tasks
        }
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
div.card {
    margin-top: 20px;
    margin-left: 90px;
    margin-right: 90px;
    
}
.card-title {
    font-size: 120%;
}
.my-custom-button {
    font-size: 15px; /* 文字大小 */
   
    margin-left: 15px;
}
div.error_message {
    color: red;
}
.table-container {
    max-height: 500px; /* 可根据需要调整 */
    overflow-y: auto;  /* 启用垂直滚动 */
    
}

.table thead th {
    position: sticky; /* 使表头粘性 */
    top: 0;          /* 固定在顶部 */
    background-color: #f8f9fa; /* 背景色，与Bootstrap一致 */
    z-index: 2;     /* 确保在内容上方 */
}
</style>