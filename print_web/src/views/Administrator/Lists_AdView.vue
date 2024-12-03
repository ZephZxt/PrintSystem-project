<template>
    <div class="background">
        <NavBar_A/>
        <div class="card">
            <div class="card-header">
                <span class="card-title">采购清单管理</span>
                <button type="button" class="btn btn-success my-custom-button" @click="refresh_lists(1)">采购已完成</button>
                <button type="button" class="btn btn-secondary my-custom-button" @click="refresh_lists(0)">采购未完成</button>
                <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add-list-modal">
                    添加采购清单
                </button>
                <!-- Modal -->
                <div class="modal fade" id="add-list-modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5">添加采购清单</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                            <label for="add-list-mName" class="form-label">采购材料名称</label>
                            <input v-model="listadd.mName" type="text" class="form-control" id="add-list-mName">
                            </div>
                            <div class="mb-3">
                            <label for="add-list-mNum" class="form-label">采购材料数量</label>
                            <input v-model="listadd.mNum" type="text" class="form-control" id="add-dep-mNum">
                            </div>
                            <div class="mb-3">
                            <label for="add-list-price" class="form-label">采购材料单价</label>
                            <input v-model="listadd.price" type="text" class="form-control" id="add-list-price">
                            </div>
                            <div class="mb-3">
                            <label for="add-list-date" class="form-label">采购材料时间</label>
                            <input v-model="listadd.date" type="text" class="form-control" id="add-list-date">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="error_message">{{ listadd.error_message }}</div>
                            <button type="button" class="btn btn-primary" @click="add_list">提交</button>
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
                                <th>采购清单编号</th>
                                <th>采购材料名称</th>
                                <th>采购材料数量</th>
                                <th>采购材料单价</th>
                                <th>采购材料时间</th>
                                <th>采购是否完成</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="list in lists" :key="list.lno">
                                <td>{{ list.lno }}</td>
                                <td>{{ list.mname }}</td>
                                <td>{{ list.mnum }}</td>
                                <td>{{ list.price }}</td>
                                <td>{{ list.date }}</td>
                                <td>{{ list.state }}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary" style="margin-right: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-list-modal-' + list.lno">
                                        修改
                                    </button>
                                    <button type="button" class="btn btn-danger" @click="remove_list(list)">
                                        删除
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" :id="'update-list-modal-' + list.lno" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5">修改采购清单</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                <label for="update-list-mName" class="form-label">采购材料名称</label>
                                                <input v-model="list.mname" type="text" class="form-control" id="update-list-mName">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-list-mNum" class="form-label">采购材料数量</label>
                                                <input v-model="list.mnum" type="text" class="form-control" id="update-list-mNum">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-list-price" class="form-label">采购材料单价</label>
                                                <input v-model="list.price" type="text" class="form-control" id="update-list-price">
                                                </div>
                                                <div class="mb-3">
                                                <label for="update-list-date" class="form-label">采购材料时间</label>
                                                <input v-model="list.date" type="text" class="form-control" id="update-list-date">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="error_message">{{ list.error_message }}</div>
                                                <button type="button" class="btn btn-primary" @click="update_list(list)">保存修改</button>
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
import $ from "jquery";
import { ref, reactive } from "vue";
import { useStore } from "vuex";
import { Modal } from "bootstrap/dist/js/bootstrap";
export default{
    components: {
        NavBar_A,
    },
    setup() {
        const store = useStore();

        let lists = ref([]);

        let listadd = reactive({
            mName: "",
            mNum: "",
            price: "",
            date: "",
            error_message: "",
        
        });

        const refresh_lists = (filter) => {
            $.ajax({
                url: "http://127.0.0.1:4000/checklists/get/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                data : {
                    filter: filter,
                },
                success(resp) {
                    lists.value = resp;
                }
            })
        }
        refresh_lists(2);

        const add_list = () => {
            listadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:4000/checklists/add/",
                type: "POST",
                data: {
                    mName: listadd.mName,
                    mNum: listadd.mNum,
                    price: listadd.price,
                    date: listadd.date
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        listadd.mName = "";
                        listadd.mNum = "";
                        listadd.price = "";
                        listadd.date = "";
                        Modal.getInstance(document.querySelector('#add-list-modal')).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                        refresh_lists();
                    } else {
                        listadd.error_message = resp.error_message;
                    }
                }
            })
        }

        const update_list = (list) => {
            listadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:4000/checklists/update/",
                type: "POST",
                data: {
                    lno: list.lno,
                    mName: list.mname,
                    mNum: list.mnum,
                    price: list.price,
                    date: list.date,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        Modal.getInstance(document.querySelector('#update-list-modal-' + list.lno)).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                        refresh_lists();
                    } else {
                        list.error_message = resp.error_message;
                    }
                }
            })
        }

        const remove_list = (list) => {
            if (confirm("确认要删除该部门吗？")) { 
            $.ajax({
                url: "http://127.0.0.1:4000/checklists/remove/",
                type: "POST",
                data: {
                    lno: list.lno,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        alert("删除成功！");
                        refresh_lists();
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
            lists,
            listadd,
            add_list,
            update_list,
            remove_list,
            refresh_lists,
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