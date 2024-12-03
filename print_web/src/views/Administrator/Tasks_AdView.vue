<template>
    <div class="background">
        <NavBar_A/>
        <div class="card">
            <div class="card-header">
                <span class="card-title">全部印刷印刷任务</span>
                <button type="button" class="btn btn-success my-custom-button" @click="refresh_taskss(1)">已完成</button>
                <button type="button" class="btn btn-secondary my-custom-button" @click="refresh_tasks(0)">未开始</button>
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
                                <th>操作</th>
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
                                <td>{{ task.state }}</td>
                                <td>
                                    <!-- <button type="button" class="btn btn-secondary" style="margin-right: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-doc-modal-' + doc.dno">
                                        修改
                                    </button>
                                    <button type="button" class="btn btn-danger" @click="remove_doc(doc)">
                                        删除
                                    </button> -->

                                    <!-- Modal -->
                                    <!-- <div class="modal fade" :id="'update-doc-modal-' + doc.dno" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5">修改印刷单据</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                <label for="update-doc-pname" class="form-label">出版商名称</label>
                                                <input v-model="doc.pname" type="text" class="form-control" id="update-doc-pname">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-doc-bname" class="form-label">书籍名称</label>
                                                <input v-model="doc.bname" type="text" class="form-control" id="update-doc-bname">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-doc-font" class="form-label">印刷字体</label>
                                                <input v-model="doc.font" type="text" class="form-control" id="update-doc-font">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-doc-mname" class="form-label">印刷材料名称</label>
                                                <input v-model="doc.mname" type="text" class="form-control" id="update-doc-mname">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-doc-num" class="form-label">印刷数量</label>
                                                <input v-model="doc.num" type="text" class="form-control" id="update-doc-num">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="error_message">{{ doc.error_message }}</div>
                                                <button type="button" class="btn btn-primary" @click="update_doc(doc)">保存修改</button>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                            </div>
                                            </div>
                                        </div>
                                    </div> -->
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

import NavBar_A from "@/components/NavBar_A.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
export default{
    components: {
        NavBar_A,
    },
    setup() {
        const store = useStore();
        let tasks = ref([]);

        const refresh_tasks = () => {
            $.ajax({
                url: "http://127.0.0.1:4000/tasks/get/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                data : {

                },
                success(resp) {
                    tasks.value = resp;
                    console.log(resp);
                }
            })
        }
        refresh_tasks();

        return {
            refresh_tasks,
            tasks,
        }
        
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