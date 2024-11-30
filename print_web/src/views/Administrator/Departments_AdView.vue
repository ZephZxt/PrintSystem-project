<template>   
    <div class="background">
        <NavBar_A/>
        <div class="card">
            <div class="card-header">
                <span class="card-title">印刷部门管理</span>
                <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add-dep-modal">
                    添加部门
                </button>
                <!-- Modal -->
                <div class="modal fade" id="add-dep-modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5">添加印刷部门</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                            <label for="add-dep-pdname" class="form-label">部门名称</label>
                            <input v-model="depadd.pdname" type="text" class="form-control" id="add-dep-pdname">
                            </div>
                            <div class="mb-3">
                            <label for="add-dep-adress" class="form-label">部门地址</label>
                            <input v-model="depadd.address" type="text" class="form-control" id="add-dep-adress">
                            </div>
                            <div class="mb-3">
                            <label for="add-dep-telenumber" class="form-label">部门电话</label>
                            <input v-model="depadd.telenumber" type="text" class="form-control" id="add-dep-telenumber">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="error_message">{{ depadd.error_message }}</div>
                            <button type="button" class="btn btn-primary" @click="add_dep">提交</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>部门编号</th>
                                <th>部门名称</th>
                                <th>部门地址</th>
                                <th>部门电话</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="dep in deps" :key="dep.pdNo">
                                <td>{{ dep.pdNo }}</td>
                                <td>{{ dep.pdName }}</td>
                                <td>{{ dep.address }}</td>
                                <td>{{ dep.teleNumber }}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary" style="margin-right: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-dep-modal-' + dep.pdNo">
                                        修改
                                    </button>
                                    <button type="button" class="btn btn-danger" @click="remove_dep(dep)">
                                        删除
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" :id="'update-dep-modal-' + dep.pdNo" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5">修改印刷部门</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                <label for="update-dep-pdname" class="form-label">部门名称</label>
                                                <input v-model="dep.pdName" type="text" class="form-control" id="update-dep-pdname">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-dep-adress" class="form-label">部门地址</label>
                                                <input v-model="dep.address" type="text" class="form-control" id="update-dep-adress">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-dep-telenumber" class="form-label">部门电话</label>
                                                <input v-model="dep.teleNumber" type="text" class="form-control" id="update-dep-telenumber">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="error_message">{{ dep.error_message }}</div>
                                                <button type="button" class="btn btn-primary" @click="update_dep(dep)">保存修改</button>
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
import $ from "jquery";
import NavBar_A from "@/components/NavBar_A.vue";
import { ref, reactive } from "vue";

import { useStore } from "vuex";
import { Modal } from "bootstrap/dist/js/bootstrap";
export default{
    components: {
        NavBar_A,
    },
    setup() {
        const store = useStore();
        let deps = ref([]);

        const depadd = reactive({
            pdname: "",
            address: "",
            telenumber: "",
            error_message: "",
        });

        const refresh_deps = () => {
            $.ajax({
                url: "http://127.0.0.1:4000/departments/get/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    deps.value = resp;
                }
            })
        }
        refresh_deps();

        const add_dep = () => {
            depadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:4000/departments/add/",
                type: "POST",
                data: {
                    pdName: depadd.pdname,
                    address: depadd.address,
                    teleNumber: depadd.telenumber,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        depadd.pdname = "";
                        depadd.address = "";
                        depadd.telenumber = "";
                        Modal.getInstance(document.querySelector('#add-dep-modal')).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                        refresh_deps();
                    } else {
                        depadd.error_message = resp.error_message;
                    }
                }
            })
        }

        const update_dep = (dep) => {
            depadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:4000/departments/update/",
                type: "POST",
                data: {
                    dep_no: dep.pdNo,
                    pdName: dep.pdName,
                    address: dep.address,
                    teleNumber: dep.teleNumber,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        Modal.getInstance(document.querySelector('#update-dep-modal-' + dep.pdNo)).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                        refresh_deps();
                    } else {
                        dep.error_message = resp.error_message;
                    }
                }
            })
        }

        const remove_dep = (dep) => {
            if (confirm("确认要删除该部门吗？")) { 
            $.ajax({
                url: "http://127.0.0.1:4000/departments/remove/",
                type: "POST",
                data: {
                    dep_no: dep.pdNo,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        alert("删除成功！");
                        refresh_deps();
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

        return {
            deps,
            depadd,
            add_dep,
            update_dep,
            remove_dep,
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