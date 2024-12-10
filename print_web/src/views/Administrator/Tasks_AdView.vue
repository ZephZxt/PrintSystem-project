<template>
    <div class="background">
        <NavBar_A/>
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
                                <td>
                                    <span v-if="task.state == 0" style="color: red;">未开始</span>
                                    <span v-if="task.state == 2" style="color: blue;">进行中</span>
                                    <span v-if="task.state == 1" style="color: green;">已完成</span>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-danger" @click="remove_task(task)" v-if="task.state == 1">
                                        删除
                                    </button>
                                    <button type="button" class="btn btn-info" @click="task_finish(task)" v-if="task.state == 2">
                                        完成印刷
                                    </button>
                                    <button type="button" class="btn btn-info" @click="task_start(task)" v-if="task.state == 0">
                                        开始印刷
                                    </button>
                                    <button type="button" class="btn btn-secondary" style="margin-top: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-task-modal-' + task.tno" v-if="task.state == 0 || task.state == 2">
                                        修改
                                    </button>
                                    

                                    <!-- Modal -->
                                    <div class="modal fade" :id="'update-task-modal-' + task.tno" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5">修改印刷任务</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                <label for="update-task-pdno" class="form-label">印刷部门编号</label>
                                                <input v-model="task.pdNo" type="text" class="form-control" id="update-task-pdno">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-task-time" class="form-label">预计完成时间</label>
                                                <input v-model="task.time" type="text" class="form-control" id="update-task-time">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="error_message">{{ task.error_message }}</div>
                                                <button type="button" class="btn btn-primary" @click="update_task(task)">保存修改</button>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                            </div>
                                            </div>
                                        </div>
                                    </div>
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
import { Modal } from "bootstrap/dist/js/bootstrap";
export default{
    components: {
        NavBar_A,
    },
    setup() {
        const store = useStore();
        let tasks = ref([]);

        const refresh_tasks = (filter) => {
            $.ajax({
                url: "http://127.0.0.1:4000/tasks/get/",
                type: "GET",
                data: {
                    filter:filter,
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

        const remove_task = (task) => {
            if (confirm("确认删除该任务吗？")) { 
            $.ajax({
                url: "http://127.0.0.1:4000/tasks/remove/",
                type: "POST",
                data: {
                    task_no: task.tno,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        
                        alert("删除成功！");
                        refresh_tasks(3);
                    }
                    else {
                        alert("删除失败：" + resp.error_message);
                    }
                }
            })
        }
            else {
                console.log("用户取消了删除操作");
            }

    }

    const task_finish = (task) => {
        $.ajax({
            url: "http://127.0.0.1:4000/tasks/update/state/",
            type: "POST",
            data: {
                task_no: task.tno,
                state: 1,
            },
            headers: {
                Authorization: "Bearer " + store.state.user.token,  
            },
            success(resp) {
                if(resp.error_message === "success") {
                    alert("任务完成！");
                    refresh_tasks(3);
                }
                else {
                    alert("任务完成失败：" + resp.error_message);
                }
            }
        })
    }

    const task_start = (task) => {
        $.ajax({
            url: "http://127.0.0.1:4000/tasks/update/state/",
            type: "POST",
            data: {
                task_no: task.tno,
                state: 2,
            },
            headers: {
                Authorization: "Bearer " + store.state.user.token,  
            },
            success(resp) {
                if(resp.error_message === "success") {
                    alert("任务开始！");
                    refresh_tasks(3);
                }
                else {
                    alert("任务开始失败：" + resp.error_message);
                }
            }
        })
    }

    const update_task = (task) => {
        $.ajax({
            url: "http://127.0.0.1:4000/tasks/update/",
            type: "POST",
            data: {
                task_no: task.tno,
                pd_no: task.pdNo,
                time: task.time,
            },
            headers: {
                Authorization: "Bearer " + store.state.user.token,  
            },
            success(resp) {
                if(resp.error_message === "success") {
                    Modal.getInstance(document.querySelector('#update-task-modal-' + task.tno)).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                    refresh_tasks(3);
                }
                else {
                    alert("修改失败：" + resp.error_message);
                }
            }
        })
    }

        return {
            refresh_tasks,
            tasks,
            remove_task,
            task_finish,
            task_start,
            update_task,
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