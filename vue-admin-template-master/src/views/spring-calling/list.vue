<template>
    <div class="app-container">
        <el-row>
          <el-col :span="8">
            <el-statistic ref="statistic" :value="this.time" title="距离春招还有" suffix="天"></el-statistic>
          </el-col>
          <el-col :span="8">
            <el-statistic ref="statistic" :value="this.postCount" title="已投简历" suffix="份"></el-statistic>
          </el-col>
          <el-col :span="8">
            <el-statistic ref="statistic" :value="this.offerCount" title="收到offer" suffix="份"></el-statistic>
          </el-col>
        </el-row>
        
        <el-divider></el-divider>
        <el-form :inline="true" :model="company" class="demo-form-inline">
            <el-form-item label="公司名称">
              <el-input v-model="company.companyName" placeholder="公司名称"></el-input>
            </el-form-item>
            <el-form-item label="公司位置">
              <el-input v-model="company.companyLocation" placeholder="公司位置"></el-input>
            </el-form-item>
            <el-form-item label="公司类型">
                <el-select v-model="company.companyStatus" clearable placeholder="公司类型">
                    <el-option :value="0" label="自研"></el-option>
                    <el-option :value="1" label="外包"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="公司规模">
                <el-select v-model="company.companyScale" clearable placeholder="公司规模">
                    <el-option :value="0" label="大厂"></el-option>
                    <el-option :value="1" label="中厂"></el-option>
                    <el-option :value="2" label="小厂"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="投递状态">
                <el-select v-model="company.status" clearable placeholder="投递状态">
                    <el-option :value="0" label="未回复"></el-option>
                    <el-option :value="1" label="一面"></el-option>
                    <el-option :value="2" label="二面"></el-option>
                    <el-option :value="3" label="三面"></el-option>
                    <el-option :value="4" label="笔试"></el-option>
                    <el-option :value="5" label="收到offer"></el-option>
                    <el-option :value="6" label="已拒"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="getCompanyList()">查询</el-button>
                <el-button type="default" icon="el-icon-refresh"  @click="resetData()">重置</el-button>
                <el-button type="warning" icon="el-icon-circle-plus-outline" @click="dialogFormVisible=true">添加</el-button>
            </el-form-item>
        </el-form>
        <el-dialog title="添加投递公司" :visible.sync="dialogFormVisible">
          <el-form :model="company">
            <el-form-item label="公司名称" clearable>
              <el-input v-model="company.companyName"></el-input>
            </el-form-item>
            <el-form-item label="公司位置" clearable>
              <el-input v-model="company.companyLocation"></el-input>
            </el-form-item>
            <el-form-item label="投递岗位" clearable>
              <el-input v-model="company.post" placeholder="投递岗位"></el-input>
            </el-form-item>
            <el-form-item label="薪资水平" clearable>
              <el-input v-model="company.salary" placeholder="薪资水平"></el-input>
            </el-form-item>
            <el-form-item label="公司类型" clearable>
              <el-select v-model="company.companyStatus" placeholder="公司类型">
                <el-option label="自研" value="0"></el-option>
                <el-option label="外包" value="1"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="公司规模" clearable>
              <el-select v-model="company.companyScale" placeholder="公司类型">
                <el-option label="大厂" value="0"></el-option>
                <el-option label="中厂" value="1"></el-option>
                <el-option label="小厂" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addCompany()">确 定</el-button>
          </div>
        </el-dialog>
        <el-table
          :data="companyList"
          style="width: 100%"
          :default-sort = "{prop: 'date', order: 'descending'}"
          >
          <el-table-column
            prop="companyName"
            label="公司名称"
            sortable
            width="250"
          >
          </el-table-column>
          <el-table-column
            prop="companyLocation"
            label="公司位置"
            sortable
            width="180"
          >
          </el-table-column>
          <el-table-column
            prop="companyStatus"
            label="公司性质"
            sortable
            width="180"></el-table-column>
          <el-table-column
            prop="companyScale"
            label="公司规模"
            sortable
            width="180"
          ></el-table-column>
          <el-table-column
            prop="post"
            label="投递岗位"
            sortable
            width="180"
          ></el-table-column>
          <el-table-column
            prop="salary"
            label="薪资"
            sortable
            width="180"
          ></el-table-column>
          <el-table-column
            prop="status"
            label="投递状态"
            sortable
            width="180"
          ></el-table-column>
          <el-table-column
            label="投递时间"
            sortable
            width="180"
          >            
            <template slot-scope="scope">
                <span>{{ renderTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                @click="findCompanyById(scope.row.id)"
                >更改投递状态</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="更改投递状态" :visible.sync="changeFormVisible">
          <el-form :model="company">
            <el-form-item label="投递状态">
              <el-select v-model="company.status" placeholder="投递状态">
                  <el-option :value="0" label="未回复"></el-option>
                  <el-option :value="1" label="一面"></el-option>
                  <el-option :value="2" label="二面"></el-option>
                  <el-option :value="3" label="三面"></el-option>
                  <el-option :value="4" label="笔试"></el-option>
                  <el-option :value="5" label="收到offer"></el-option>
                  <el-option :value="6" label="已拒"></el-option>
                </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="changeFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="changeStatus()">确 定</el-button>
          </div>
        </el-dialog>
    </div>
</template>

<script>
  import springApi from '@/api/spring'

  export default {
    data() {
      return {
        time: '',
        company: {},
        companyList: null,
        dialogFormVisible: false,
        changeFormVisible: false,
        postCount: 0,
        offerCount: 0
      }
    },
    created(){
      this.getDateFormSpringCall(),
      this.getCompanyList(),
      this.getIndexData()
    },
    methods: {
      getDateFormSpringCall(){
        springApi.getDateFormSpringCalling().then(res => {
          this.time = res.data.date
        })
      },
      resetData(){
        this.company = {},
        this.getCompanyList()
      },
      addCompany(){
        springApi.addCompany(this.company).then(res => {
          this.$message({
              type: 'success',
              message: '添加成功！'
          })
          this.dialogFormVisible = false
          this.resetData()
          this.getIndexData()
        })
      },
      getCompanyList(){
        springApi.getCompanyList(this.company).then(res => {
          this.companyList = res.data.list
          this.companyList.map((item => {
            if(item.companyStatus === '0'){
              item.companyStatus = '自研'
            }else{
              item.companyStatus = '外包'
            }
            if(item.companyScale === '0'){
              item.companyScale = '大厂'
            }else if(item.companyScale === '1'){
              item.companyScale = '中厂'
            }else {
              item.companyScale = '小厂'
            }
            if(item.status === '0'){
              item.status = '未回复'
            }else if(item.status === '1'){
              item.status = '一面'
            }else if(item.status === '2'){
              item.status = '二面'
            }else if(item.status === '3'){
              item.status = '三面'
            }else if(item.status === '4'){
              item.status = '笔试'
            }else if(item.status === '5'){
              item.status = '收到offer'
            }else {
              item.status = '已拒'
            }
          })),
          this.getIndexData()
        })
      },
      changeStatus(){
        springApi.changeStatus(this.company).then(res => {
          this.$message({
              type: 'success',
              message: '修改成功！'
          })
          this.resetData()
          this.changeFormVisible = false
        })
      },
      renderTime(date) {
          var dateee = new Date(date).toJSON();
          return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
      },
      findCompanyById(id){
        this.changeFormVisible = true
        springApi.getCompanyById(id).then(res => {
          this.company = res.data.company
        })
      },
      getIndexData(){
        springApi.getIndexData(this.company).then(res => {
          this.postCount = res.data.postCount
          this.offerCount = res.data.offerCount
        })
      }
    }
  }
</script>